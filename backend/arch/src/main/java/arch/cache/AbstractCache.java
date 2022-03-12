package arch.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class AbstractCache<K, T> {

    private final static String NULL_KEY = "[Cache Manager] Key is null!";
    private static final Logger _LOGGER = LoggerFactory.getLogger(AbstractCache.class);

    private final HazelcastInstance hazelcastInstance;
    private final String mapName;
    private final IMap<K, T> cacheMap;

    public AbstractCache(HazelcastInstance hazelcastInstance, String mapName) {
        this.hazelcastInstance = hazelcastInstance;
        this.mapName = mapName;
        this.cacheMap = hazelcastInstance.getMap(mapName);
    }

    public AbstractCache(String mapName) {
        this.hazelcastInstance = Hazelcast.newHazelcastInstance();
        this.mapName = mapName;
        this.cacheMap = hazelcastInstance.getMap(mapName);
    }

    public void putCacheData(K key, T value) {
        putCacheData(key, value, true);
    }

    public void putCacheData(K key, T value, boolean log) {
        Objects.requireNonNull(key, NULL_KEY);
        if (log)
            _LOGGER.info("Upsert cache data..");
        cacheMap.put(key, value);
    }

    public T getCacheData(K key) throws CacheElementNotFoundException {
        Objects.requireNonNull(key, NULL_KEY);

        T obj = cacheMap.get(key);
        if (obj == null) {
            _LOGGER.info("Data not found in cache..");
            throw new CacheElementNotFoundException();
        }
        return obj;
    }

    public void removeCacheData(K key) {
        Objects.requireNonNull(key, NULL_KEY);
        _LOGGER.info("Removing cache data..");
        cacheMap.remove(key);
    }

    public Set<K> getKeySet() {
        return cacheMap.keySet().size() != 0 ? cacheMap.keySet() : new HashSet<>();
    }

    public List<T> getAll() {
        _LOGGER.info("Retrieving all cached data..");
        List<T> mapContent = new ArrayList<>();
        for (K key : getKeySet()) {
            mapContent.add(cacheMap.get(key));
        }
        return mapContent;
    }

    public int count() {
        return cacheMap.size();
    }
}
