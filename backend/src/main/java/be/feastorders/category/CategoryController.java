package be.feastorders.category;

import be.feastorders.category.dto.CategoryDTO;
import be.feastorders.category.entity.Category;
import be.feastorders.category.service.CategoryService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/category"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService service;

    @ApiOperation("get categories")
    @ApiResponse(code = 200, message = "categories found", response = List.class)
    @GetMapping
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categories = service.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
        return categories;
    }

    @ApiOperation("get category")
    @ApiResponse(code = 200, message = "category found", response = CategoryDTO.class)
    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        Category category = service.read(id);
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
        category = service.create(category);
        return new CategoryDTO(category);
    }

    @ApiOperation("update category")
    @ApiResponse(code = 200, message = "category updated", response = CategoryDTO.class)
    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = service.read(id);
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setDescription(categoryDTO.getDescription());

        category = service.update(category);
        return new CategoryDTO(category);
    }

    @ApiOperation("delete category")
    @ApiResponse(code = 200, message = "category deleted", response = CategoryDTO.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (Objects.isNull(id)) {
            ResponseEntity.badRequest().build();
        }

        if (service.delete(id)) {
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

        CategoryDTO dto = new CategoryDTO(service.read(categoryID));

        return ResponseEntity.ok(dto.getMenuItemList());

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

        CategoryDTO dto = new CategoryDTO(service.read(categoryID));

        if (CollectionUtils.isEmpty(dto.getMenuItemList()))
            return ResponseEntity.ok().build();


        Optional<MenuItemDTO> menuItemOptional = dto.getMenuItemList().stream()
                .filter(m -> m.getID().equals(itemID))
                .findFirst();

        return menuItemOptional
                .map(opt -> ResponseEntity.ok().body(opt))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("create menu item into selected category")
    @ApiResponse(code = 200, message = "list of menu item created", response = ResponseEntity.class)
    @PostMapping("/{id}/menuitem")
    public ResponseEntity<CategoryDTO> createMenuItem(@RequestBody List<MenuItemDTO> menuItemDTOList,
                                                      @ApiParam(name = "category ID",
                                                              type = "Long",
                                                              allowEmptyValue = false,
                                                              required = true,
                                                              value = "The selected category")
                                                      @PathVariable("id") Long categoryID) {

        Category category = service.read(categoryID);

        List<MenuItem> menuItemList = menuItemDTOList.stream()
                .map(CategoryService::menuItemDTO2Entity)
                .collect(Collectors.toList());

        for (MenuItem mn : menuItemList) {
            mn.setCategory(category);
        }

        category.getMenuItems().addAll(menuItemList);
        return ResponseEntity.ok(new CategoryDTO(service.update(category)));

    }

    @ApiOperation("update menu item with new properties by category id and item id")
    @ApiResponse(code = 200, message = "menu item updated", response = ResponseEntity.class)
    @PutMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<CategoryDTO> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO,
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

        Category category = service.read(categoryID);

        category.getMenuItems().stream()
                .filter(dto -> dto.getID().equals(itemID))
                .findFirst()
                .ifPresentOrElse(item -> CategoryService
                                .updateMenuItemEntityProperties(item, CategoryService.menuItemDTO2Entity(menuItemDTO)),
                        () -> {
                            throw new EntityNotFoundException("Entity not found for ID:" + itemID);
                        });

        return ResponseEntity.ok(new CategoryDTO(service.update(category)));

    }

    @ApiOperation("remove one menu item from category")
    @ApiResponse(code = 200, message = "menu item deleted", response = ResponseEntity.class)
    @DeleteMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<CategoryDTO> deleteMenuItemByIdAndCategoryId(
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

        Category category = service.read(categoryID);

        List<MenuItem> menuItemList = category.getMenuItems().stream()
                .filter(dto -> !dto.getID().equals(itemID))
                .collect(Collectors.toList());

        category.getMenuItems().clear();
        category.getMenuItems().addAll(menuItemList);
        return ResponseEntity.ok(new CategoryDTO(service.update(category)));
    }
}
