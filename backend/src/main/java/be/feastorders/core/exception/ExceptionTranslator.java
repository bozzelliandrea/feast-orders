package be.feastorders.core.exception;

import be.feastorders.core.exception.errors.HttpFeastServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler({HttpFeastServerException.class})
    protected ResponseEntity<FeastErrorResponse> handleHttpFeastServerException(HttpFeastServerException exception, WebRequest request) {
        return this._buildError(exception);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<FeastErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        return this._buildError(new HttpFeastServerException(exception.getMessage()));
    }

    private ResponseEntity<FeastErrorResponse> _buildError(HttpFeastServerException exception) {

        HttpStatus httpCode = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorMessage;

        if (exception.getClass().isAnnotationPresent(ResponseStatus.class)) {
            httpCode = exception.getClass().getAnnotation(ResponseStatus.class).code();
        }

        if (exception.getMessage() == null) {
            errorMessage = exception.getClass().getName();
        } else {
            errorMessage = exception.getMessage();
        }

        final FeastErrorResponse err = new FeastErrorResponse(
                LocalDateTime.now().toString(),
                httpCode.value(),
                httpCode.getReasonPhrase(),
                errorMessage
        );

        return ResponseEntity.status(httpCode).body(err);
    }
}
