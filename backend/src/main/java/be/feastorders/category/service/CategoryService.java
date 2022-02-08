package be.feastorders.category.service;

import be.feastorders.category.dto.CategoryDTO;
import be.feastorders.category.dto.CategoryProcessingZone;
import be.feastorders.category.entity.Category;
import be.feastorders.category.repository.CategoryRepository;
import be.feastorders.core.service.BaseCRUDService;
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
