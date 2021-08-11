package be.feastorders.category.service;

import be.feastorders.category.entity.Category;
import be.feastorders.category.repository.CategoryRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(6)
public class CategoryService extends BaseCRUDService<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
