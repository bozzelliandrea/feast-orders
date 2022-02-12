package business.validator;

import arch.exception.errors.InvalidRequestException;
import arch.validation.AbstractRESTValidator;
import arch.validation.Validator;
import business.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator extends AbstractRESTValidator<CategoryDTO> implements Validator<CategoryDTO> {

    @Override
    public void create(CategoryDTO request) {
        validateInstance(request);

        if (request.getName() == null) {
            throw new InvalidRequestException("Category Id cannot be null!");
        }
    }

    @Override
    public void update(CategoryDTO request) {
        validateInstance(request);
        baseValidation(request);

        if (request.getId() == null) {
            throw new InvalidRequestException("Category Id cannot be null!");
        }
    }

    @Override
    public void get(Long id) {
        if (id == null) {
            throw new InvalidRequestException("Category Id cannot be null!");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new InvalidRequestException("Category Id cannot be null!");
        }
    }
}
