package be.feastorders.business.category.service;

import be.feastorders.business.category.entity.Category;
import be.feastorders.business.category.repository.CategoryRepository;
import be.feastorders.business.core.service.BaseCRUDService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(6)
public class CategoryService extends BaseCRUDService<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
