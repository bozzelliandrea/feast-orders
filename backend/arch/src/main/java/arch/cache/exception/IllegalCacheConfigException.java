package arch.cache.exception;

public class IllegalCacheConfigException extends RuntimeException {
    public IllegalCacheConfigException() {
    }

    public IllegalCacheConfigException(String message) {
        super(message);
    }
}
