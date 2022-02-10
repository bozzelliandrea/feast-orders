package business.service;

import arch.service.BaseCRUDService;
import atomic.entity.Category;
import atomic.enums.CategoryProcessingZone;
import atomic.repository.CategoryRepository;
import business.dto.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseCRUDService<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    static public Category categoryDTO2Category(CategoryDTO dto) {

        Category entity = new Category();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setColor(dto.getColor());
        entity.setProcessingZone(CategoryProcessingZone.valueOf(dto.getProcessingZone()));
        return entity;
    }

}
