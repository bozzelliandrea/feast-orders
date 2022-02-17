package controller;

import arch.security.annotation.Admin;
import atomic.entity.Category;
import atomic.entity.MenuItem;
import business.category.dto.CategoryDTO;
import business.category.service.CategoryService;
import business.menuitem.dto.MenuItemDTO;
import business.menuitem.service.MenuItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/category"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuItemService menuItemService;

    @ApiOperation("get categories")
    @ApiResponse(code = 200, message = "categories found", response = List.class)
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @ApiOperation("get category")
    @ApiResponse(code = 200, message = "category found", response = CategoryDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @ApiOperation(value = "create category", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "category created", response = CategoryDTO.class)
    @PostMapping
    @Admin
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @ApiOperation(value = "update category", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "category updated", response = CategoryDTO.class)
    @PutMapping
    @Admin
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.update(categoryDTO));
    }

    @ApiOperation(value = "delete category", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "category deleted", response = CategoryDTO.class)
    @DeleteMapping("/{id}")
    @Admin
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.remove(id));
    }

    @ApiOperation("Get all menu item associated with the selected category")
    @ApiResponse(code = 200, message = "menu item list returned", response = ResponseEntity.class)
    @GetMapping("/{id}/menuitem")
    public ResponseEntity<List<MenuItemDTO>> findAllMenuItemByCategoryId(
            @ApiParam(name = "category ID",
                    type = "Long",
                    allowEmptyValue = false,
                    required = true,
                    value = "The selected category")
            @PathVariable("id") Long categoryID) {
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @ApiOperation("Get one menu item by id associated with the selected category")
    @ApiResponse(code = 200, message = "menu item list returned", response = ResponseEntity.class)
    @GetMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<MenuItemDTO> getMenuItemByCategoryId(
            @ApiParam(name = "category ID",
                    type = "Long",
                    required = true,
                    value = "The selected category")
            @PathVariable("id") Long categoryID,
            @ApiParam(name = "menu item ID",
                    type = "Long",
                    required = true,
                    value = "The selected menu item to retrieve")
            @PathVariable("itemId") Long id) {
        return ResponseEntity.ok(menuItemService.get(id));
    }

    @ApiOperation(value = "create menu item into selected category", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "list of menu item created", response = ResponseEntity.class)
    @PostMapping("/{id}/menuitem")
    @Admin
    public ResponseEntity<List<MenuItemDTO>> createMenuItem(@RequestBody MenuItemDTO menuItemDTO,
                                                            @ApiParam(name = "category ID",
                                                                    type = "Long",
                                                                    allowEmptyValue = false,
                                                                    required = true,
                                                                    value = "The selected category")
                                                            @PathVariable("id") Long categoryID) {
        Category category = categoryService.read(categoryID);
        menuItemService.saveMenuItemDTOWithCategory(menuItemDTO, category);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @ApiOperation(value = "update menu item with new properties by category id and item id", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "menu item updated", response = ResponseEntity.class)
    @PutMapping("/{id}/menuitem/{itemId}")
    @Admin
    public ResponseEntity<List<MenuItemDTO>> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO,
                                                            @ApiParam(name = "category ID",
                                                                    type = "Long",
                                                                    allowEmptyValue = false,
                                                                    required = true,
                                                                    value = "The selected category")
                                                            @PathVariable("id") Long categoryID,
                                                            @ApiParam(name = "menu item ID",
                                                                    type = "Long",
                                                                    required = true,
                                                                    value = "The selected menu item to retrieve")
                                                            @PathVariable("itemId") Long itemID) {
        MenuItem oldMenuItem = menuItemService.read(itemID);
        MenuItemService.updateMenuItemEntityProperties(oldMenuItem,
                MenuItemService.menuItemDTO2Entity(menuItemDTO));
        menuItemService.update(oldMenuItem);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }

    @ApiOperation(value = "remove one menu item from category", authorizations = @Authorization("ADMIN"))
    @ApiResponse(code = 200, message = "menu item deleted", response = ResponseEntity.class)
    @DeleteMapping("/{id}/menuitem/{itemId}")
    @Admin
    public ResponseEntity<List<MenuItemDTO>> deleteMenuItemByIdAndCategoryId(
            @ApiParam(name = "category ID",
                    type = "Long",
                    required = true,
                    value = "The selected category")
            @PathVariable("id") Long categoryID,
            @ApiParam(name = "menu item ID",
                    type = "Long",
                    required = true,
                    value = "The selected menu item to retrieve")
            @PathVariable("itemId") Long itemID) {
        menuItemService.delete(itemID);
        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));
    }
}
