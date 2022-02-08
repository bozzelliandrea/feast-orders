package be.feastorders.core.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends HttpFeastServerException {

    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }

}
