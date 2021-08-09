package be.feastorders.business.category.service;

import be.feastorders.business.category.entity.Category;
import be.feastorders.business.category.repository.CategoryRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseCRUDService<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
