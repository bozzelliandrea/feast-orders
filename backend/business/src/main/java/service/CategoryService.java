package service;

import dto.CategoryDTO;
import entity.Category;
import enums.CategoryProcessingZone;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;

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
