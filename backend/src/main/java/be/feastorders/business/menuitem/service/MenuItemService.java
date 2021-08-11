package be.feastorders.business.menuitem.service;

import be.feastorders.business.core.service.BaseCRUDService;
import be.feastorders.business.menuitem.entity.MenuItem;
import be.feastorders.business.menuitem.repository.MenuItemRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class MenuItemService extends BaseCRUDService<MenuItem, Long> {

    public MenuItemService(MenuItemRepository repository) {
        super(repository);
    }
}
