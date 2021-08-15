package be.feastorders.menuitem.service;

import be.feastorders.category.entity.Category;
import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.menuitem.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuItemService extends BaseCRUDService<MenuItem, Long> {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void saveMenuItemDTOListWithCategory(List<MenuItemDTO> menuItemDTOList, Category category) {

        category.getMenuItems().clear();

        List<MenuItem> menuItemList = new ArrayList<>();

        for (MenuItemDTO dto : menuItemDTOList) {
            MenuItem mn = menuItemDTO2Entity(dto);
            mn.setCategory(category);
            menuItemList.add(mn);
        }

        repository.saveAll(menuItemList);
    }

    public List<MenuItemDTO> findAllMenuItemByCategoryId(Long categoryID) {

        Objects.requireNonNull(categoryID, "Category ID param cannot be null!");

        List<MenuItem> entityList = repository.findByCategoryID(categoryID);

        if (Objects.isNull(entityList) || CollectionUtils.isEmpty(entityList)) {
            return new ArrayList<>();
        } else {
            return entityList.stream().map(MenuItemDTO::new).collect(Collectors.toList());
        }
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

    static public MenuItem menuItemDTO2Entity(MenuItemDTO dto) {

        Objects.requireNonNull(dto, "DTO param is required.");

        MenuItem entity = new MenuItem();

        if (Objects.nonNull(dto.getPrice()))
            entity.setPrice(dto.getPrice());

        if (Objects.nonNull(dto.getName()))
            entity.setName(dto.getName());

        if (Objects.nonNull(dto.getDescription()))
            entity.setDescription(dto.getDescription());

        if (Objects.nonNull(dto.getColor()))
            entity.setColor(dto.getColor());

        if (Objects.nonNull(dto.getVersion()))
            entity.setVersion(dto.getVersion());

        return entity;
    }

    public MenuItemDTO readMenuItemByOrderIdAndItemId(Long orderID, Long menuItemID) {

        Objects.requireNonNull(orderID, "Order ID param cannot be null!");
        Objects.requireNonNull(menuItemID, "Menu Item ID param cannot be null!");

        MenuItem entity = repository.readMenuItemByIdAndOrderId(orderID, menuItemID);

        if (Objects.isNull(entity)) {
            throw new EntityNotFoundException("No entity was found with ID:" + menuItemID + " for order: " + orderID);
        }

        return new MenuItemDTO(entity);
    }

    public List<MenuItemDTO> findAllMenuItemByOrderId(Long orderID) {

        Objects.requireNonNull(orderID, "Order ID param cannot be null!");

        List<MenuItem> entityList = repository.findMenuItemsByOrderId(orderID);

        if (Objects.isNull(entityList) || CollectionUtils.isEmpty(entityList)) {
            return new ArrayList<>();
        } else {
            return entityList.stream().map(MenuItemDTO::new).collect(Collectors.toList());
        }
    }

}
