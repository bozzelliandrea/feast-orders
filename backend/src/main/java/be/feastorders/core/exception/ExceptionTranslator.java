package be.feastorders.core.exception;

import be.feastorders.core.exception.errors.HttpFeastServerException;
import be.feastorders.core.exception.errors.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<FeastErrorResponse> handleOrderNotFound(OrderNotFoundException exception, WebRequest request) {
        return this._buildError(exception);
    }

    private ResponseEntity<FeastErrorResponse> _buildError(HttpFeastServerException exception) {

        HttpStatus httpCode = HttpStatus.INTERNAL_SERVER_ERROR;

        if (exception.getClass().isAnnotationPresent(ResponseStatus.class)) {
            httpCode = exception.getClass().getAnnotation(ResponseStatus.class).code();
        }

        final FeastErrorResponse err = new FeastErrorResponse(
                LocalDateTime.now(),
                httpCode.value(),
                httpCode.getReasonPhrase(),
                exception.getMessage()
        );

        return ResponseEntity.status(httpCode).body(err);
    }
}
