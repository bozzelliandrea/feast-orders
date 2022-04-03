package controller;

import arch.security.annotation.Admin;
import atomic.entity.Category;
import atomic.entity.MenuItem;
import business.category.dto.CategoryDTO;
import business.category.service.CategoryService;
import business.menuitem.dto.MenuItemDTO;
import business.menuitem.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/category"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;
    private final MenuItemService menuItemService;

    public CategoryController(CategoryService categoryService, MenuItemService menuItemService) {
        this.categoryService = categoryService;
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @Admin
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @Admin
    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.remove(id));
    }

    @GetMapping("/{id}/menuitem")
    public ResponseEntity<List<MenuItemDTO>> findAllMenuItemByCategoryId(@PathVariable("id") Long categoryID) {
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @GetMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<MenuItemDTO> getMenuItemByCategoryId(@PathVariable("id") Long categoryID,
                                                               @PathVariable("itemId") Long id) {
        return ResponseEntity.ok(menuItemService.get(id));
    }

    @Admin
    @PostMapping("/{id}/menuitem")
    public ResponseEntity<List<MenuItemDTO>> createMenuItem(@RequestBody MenuItemDTO menuItemDTO,
                                                            @PathVariable("id") Long categoryID) {
        Category category = categoryService.read(categoryID);
        menuItemService.saveMenuItemDTOWithCategory(menuItemDTO, category);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @Admin
    @PutMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<List<MenuItemDTO>> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO,
                                                            @PathVariable("id") Long categoryID,
                                                            @PathVariable("itemId") Long itemID) {
        MenuItem oldMenuItem = menuItemService.read(itemID);
        MenuItemService.updateMenuItemEntityProperties(oldMenuItem,
                MenuItemService.menuItemDTO2Entity(menuItemDTO));
        menuItemService.update(oldMenuItem);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @Admin
    @DeleteMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<List<MenuItemDTO>> deleteMenuItemByIdAndCategoryId(@PathVariable("id") Long categoryID,
                                                                             @PathVariable("itemId") Long itemID) {
        menuItemService.delete(itemID);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }
}
