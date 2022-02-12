package arch.validation;

import arch.dto.AbstractDTO;
import arch.exception.errors.InvalidRequestException;

import java.util.Objects;

public interface Validator<T> {

    default void create(T request) {
    }

    default void update(T request) {
    }

    default void get(Long id) {
    }

    default void delete(Long id) {
    }

    default void validateInstance(T obj) {

        if (obj == null) {
            throw new InvalidRequestException("Request is null!");
        }

        if (!(obj instanceof AbstractDTO)) {
            throw new InvalidRequestException("Request format is invalid!");
        }
    }


    default void baseValidation(AbstractDTO obj) {
        try {
            Objects.requireNonNull(obj);
            Objects.requireNonNull(obj.getVersion());
            Objects.requireNonNull(obj.getCreationTimestamp());
            Objects.requireNonNull(obj.getCreationUser());
        } catch (NullPointerException exception) {
            throw new InvalidRequestException(exception.getMessage());
        }
    }
}
