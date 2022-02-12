package business.exception;

import arch.exception.errors.HttpFeastServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OrderUpdateException extends HttpFeastServerException {

    public OrderUpdateException() {
    }

    public OrderUpdateException(String message) {
        super(message);
    }
}
