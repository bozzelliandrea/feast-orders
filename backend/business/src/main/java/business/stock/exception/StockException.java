package business.stock.exception;

import arch.exception.errors.HttpFeastServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StockException extends HttpFeastServerException {

    public StockException(String message) {
        super(message);
    }
}
