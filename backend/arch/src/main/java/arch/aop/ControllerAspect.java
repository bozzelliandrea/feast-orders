package arch.aop;

import arch.validation.RequiredMethod;
import arch.validation.RequiredSupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class ControllerAspect {

    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
    public void requestMappingAnnotations() {
    }

    @Before("requestMappingAnnotations()")
    public void requestInterceptor(JoinPoint joinPoint) throws Exception {

        final Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(RequestMapping.class)) {
                Object api = annotation.getClass().getMethod("value").invoke(annotation);
                if (((String[]) api).length == 0)
                    logger.info("Receive {} request type", annotation.annotationType().getSimpleName());
                else
                    logger.info("Receive {} request type for url {}", annotation.annotationType().getSimpleName(), api);

                RequiredMethod rm = RequiredMethod.getByAnnotation(annotation.annotationType());
                Object[] request = joinPoint.getArgs();

                for (Object o : request) {
                    if (RequiredSupport.isPrimitiveWrapper(o.getClass())) {
                        RequiredSupport.validate(o, method);
                    } else {
                        RequiredSupport.validate(o, rm);
                    }
                }
            }
        }
    }
}
