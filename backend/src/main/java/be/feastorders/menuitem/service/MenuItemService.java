package be.feastorders.menuitem.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.menuitem.repository.MenuItemRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class MenuItemService extends BaseCRUDService<MenuItem, Long> {

    public MenuItemService(MenuItemRepository repository) {
        super(repository);
    }
}
