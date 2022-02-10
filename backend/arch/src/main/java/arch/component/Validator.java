package arch.component;

import arch.dto.AbstractDTO;
import arch.exception.errors.InvalidRequestException;

import java.util.Objects;

public interface Validator<T> {

    default void validateOnCreate(T obj) {

        if (!(obj instanceof AbstractDTO)) {
            throw new InvalidRequestException("Request format is invalid!");
        } else {
            AbstractDTO request = (AbstractDTO) obj;
        }
    }


    private void _internalValidation(AbstractDTO obj) {
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
