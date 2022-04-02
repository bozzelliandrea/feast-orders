package arch.cache;

import arch.exception.errors.HttpFeastServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationConfigurationException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Primary
@Component
public final class CacheConfigRepository implements InitializingBean {

    private static final Logger _LOGGER = LoggerFactory.getLogger(CacheConfigRepository.class);
    private static final Map<String, ScheduledExecutorService> configuredCache = new HashMap<>();

    private final ApplicationContext appContext;

    private static CacheConfigRepository instance = null;

    public static CacheConfigRepository getInstance() {
        return instance;
    }

    private CacheConfigRepository(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    public void register(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Cache.class)) {
            final Cache config = clazz.getAnnotation(Cache.class);
            final String name = config.name();
            final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();

            switch (config.policy()) {
                case REFRESH:
                    exec.scheduleAtFixedRate(new RefreshThread(name, clazz, appContext), 0, config.minutes(), TimeUnit.MINUTES);
                    break;
                case CLEAR:
                    exec.scheduleAtFixedRate(new ClearThread(name, clazz, appContext), 0, config.minutes(), TimeUnit.MINUTES);
                    break;
                default:
                    _LOGGER.warn("No policy found for configured cache named: {}", name);
                    break;
            }

            configuredCache.put(name, exec);
            _LOGGER.info("Registered cache: {}", name);
        } else {
            throw new AnnotationConfigurationException("The cache cannot be register without config annotation");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    private static class RefreshThread implements Runnable {

        private static final Logger _LOGGER = LoggerFactory.getLogger(RefreshThread.class);

        private final String name;
        private final Class<?> clazz;
        private final ApplicationContext appContext;

        public RefreshThread(String n, Class<?> p, ApplicationContext appContext) {
            name = n;
            clazz = p;
            this.appContext = appContext;
            if (!RechargeableCache.class.isAssignableFrom(clazz)) {
                throw new IllegalCacheConfigException("Manager class did not implement rechargeable cache");
            }
        }

        @Override
        public void run() {
            try {
                _LOGGER.info("Refresh cache thread run for {}", name);
                Object instance = appContext.getBean(clazz);
                Method cleanMethod = clazz.getMethod("reload");
                cleanMethod.invoke(instance);
            } catch (Exception e) {
                throw new HttpFeastServerException(e);
            }
        }
    }

    private static class ClearThread implements Runnable {

        private final String name;
        private final Class<?> clazz;
        private final ApplicationContext appContext;

        public ClearThread(String n, Class<?> p, ApplicationContext appContext) {
            name = n;
            clazz = p;
            this.appContext = appContext;
            if (!CleanableCache.class.isAssignableFrom(clazz)) {
                throw new IllegalCacheConfigException("Manager class did not implement cleanable cache");
            }
        }

        @Override
        public void run() {
            try {
                _LOGGER.info("Clear cache thread run for {}", name);
                Object instance = appContext.getBean(clazz);
                Method cleanMethod = clazz.getMethod("clean");
                cleanMethod.invoke(instance);
            } catch (Exception e) {
                throw new HttpFeastServerException(e);
            }
        }
    }
}
