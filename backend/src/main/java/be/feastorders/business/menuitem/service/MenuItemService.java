package be.feastorders.business.menuitem.service;

import be.feastorders.business.menuitem.entity.MenuItem;
import be.feastorders.business.menuitem.repository.MenuItemRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService extends BaseCRUDService<MenuItem, Long> {

    public MenuItemService(MenuItemRepository repository) {
        super(repository);
    }
}
