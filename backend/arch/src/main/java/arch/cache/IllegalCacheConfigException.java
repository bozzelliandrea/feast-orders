package arch.cache;

public class IllegalCacheConfigException extends RuntimeException {
    public IllegalCacheConfigException() {
    }

    public IllegalCacheConfigException(String message) {
        super(message);
    }
}
