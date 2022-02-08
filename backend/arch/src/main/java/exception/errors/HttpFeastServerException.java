package exception.errors;

public class HttpFeastServerException extends RuntimeException {

    public HttpFeastServerException() {
    }

    public HttpFeastServerException(String message) {
        super(message);
    }

    public HttpFeastServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
