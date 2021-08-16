package be.feastorders.category;

import be.feastorders.category.dto.CategoryDTO;
import be.feastorders.category.entity.Category;
import be.feastorders.category.service.CategoryService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.menuitem.service.MenuItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categories = categoryService.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
        return categories;
    }

    @ApiOperation("get category")
    @ApiResponse(code = 200, message = "category found", response = CategoryDTO.class)
    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        Category category = categoryService.read(id);
        return new CategoryDTO(category);
    }

    @ApiOperation("create category")
    @ApiResponse(code = 200, message = "category created", response = CategoryDTO.class)
    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .color(categoryDTO.getColor())
                .description(categoryDTO.getDescription())
                .build();
        category = categoryService.create(category);
        return new CategoryDTO(category);
    }

    @ApiOperation("update category")
    @ApiResponse(code = 200, message = "category updated", response = CategoryDTO.class)
    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = categoryService.read(id);
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setDescription(categoryDTO.getDescription());

        category = categoryService.update(category);
        return new CategoryDTO(category);
    }

    @ApiOperation("delete category")
    @ApiResponse(code = 200, message = "category deleted", response = CategoryDTO.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (Objects.isNull(id)) {
            ResponseEntity.badRequest().build();
        }

        if (categoryService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
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
            @PathVariable("itemId") Long itemID) {

        return ResponseEntity.ok(new MenuItemDTO(menuItemService.read(itemID)));
    }

    @ApiOperation("create menu item into selected category")
    @ApiResponse(code = 200, message = "list of menu item created", response = ResponseEntity.class)
    @PostMapping("/{id}/menuitem")
    public ResponseEntity<List<MenuItemDTO>> createMenuItem(@RequestBody List<MenuItemDTO> menuItemDTOList,
                                                            @ApiParam(name = "category ID",
                                                                    type = "Long",
                                                                    allowEmptyValue = false,
                                                                    required = true,
                                                                    value = "The selected category")
                                                            @PathVariable("id") Long categoryID) {

        Category category = categoryService.read(categoryID);

        menuItemService.saveMenuItemDTOListWithCategory(menuItemDTOList, category);

        return ResponseEntity.ok(menuItemService.findAllMenuItemByCategoryId(categoryID));

    }

    @ApiOperation("update menu item with new properties by category id and item id")
    @ApiResponse(code = 200, message = "menu item updated", response = ResponseEntity.class)
    @PutMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO,
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

        return ResponseEntity.ok(new MenuItemDTO(menuItemService.update(oldMenuItem)));
    }

    @ApiOperation("remove one menu item from category")
    @ApiResponse(code = 200, message = "menu item deleted", response = ResponseEntity.class)
    @DeleteMapping("/{id}/menuitem/{itemId}")
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
