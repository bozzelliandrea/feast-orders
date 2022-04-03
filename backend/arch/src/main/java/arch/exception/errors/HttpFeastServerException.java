package arch.exception.errors;

public class HttpFeastServerException extends RuntimeException {

    public HttpFeastServerException() {
    }

    public HttpFeastServerException(Throwable cause) {
        super(cause);
    }

    public HttpFeastServerException(String message) {
        super(message);
    }

    public HttpFeastServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
