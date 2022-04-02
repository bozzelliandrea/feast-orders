package arch.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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
