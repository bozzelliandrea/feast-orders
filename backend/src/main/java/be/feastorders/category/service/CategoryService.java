package be.feastorders.category.service;

import be.feastorders.category.entity.Category;
import be.feastorders.category.repository.CategoryRepository;
import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService extends BaseCRUDService<Category, Long> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    static public MenuItem menuItemDTO2Entity(MenuItemDTO dto) {

        MenuItem entity = new MenuItem();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setColor(dto.getColor());
        return entity;
    }

    static public void updateMenuItemEntityProperties(MenuItem o, MenuItem n) {

        Objects.requireNonNull(o, "Old entity is required.");
        Objects.requireNonNull(o, "New entity is required.");

        if (Objects.nonNull(n.getPrice()))
            o.setPrice(n.getPrice());

        if (Objects.nonNull(n.getName()))
            o.setName(n.getName());

        if (Objects.nonNull(n.getDescription()))
            o.setDescription(n.getDescription());

        if (Objects.nonNull(n.getColor()))
            o.setColor(n.getColor());
    }

}
