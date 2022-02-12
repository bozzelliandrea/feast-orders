package arch.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegisterFailedException extends HttpFeastServerException {

    public RegisterFailedException() {
    }

    public RegisterFailedException(String message) {
        super(message);
    }
}
