package controller;

import atomic.entity.Category;
import atomic.entity.MenuItem;
import atomic.entity.PrinterCfg;
import atomic.enums.CategoryProcessingZone;
import business.dto.CategoryDTO;
import business.dto.MenuItemDTO;
import business.dto.PrinterCfgDTO;
import business.service.CategoryService;
import business.service.MenuItemService;
import business.service.PrinterCfgService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = {"/category"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private PrinterCfgService printerCfgService;

    @ApiOperation("get categories")
    @ApiResponse(code = 200, message = "categories found", response = List.class)
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = categoryService.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    @ApiOperation("get category")
    @ApiResponse(code = 200, message = "category found", response = CategoryDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        Category category = categoryService.read(id);
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @ApiOperation("create category")
    @ApiResponse(code = 200, message = "category created", response = CategoryDTO.class)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setProcessingZone(CategoryProcessingZone.valueOf(categoryDTO.getProcessingZone()));
        category.setColor(categoryDTO.getColor());
        if (categoryDTO.getPrinterCfgList() != null && !categoryDTO.getPrinterCfgList().isEmpty()) {
            List<PrinterCfg> printerCfgList = categoryDTO.getPrinterCfgList().stream().map(dto -> {
                return printerCfgService.read(dto.getID());
            }).collect(Collectors.toList());
            category.getPrinterCfgs().addAll(printerCfgList);
        }

        category = categoryService.create(category);
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @ApiOperation("update category")
    @ApiResponse(code = 200, message = "category updated", response = CategoryDTO.class)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = categoryService.read(id);
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setProcessingZone(CategoryProcessingZone.valueOf(categoryDTO.getProcessingZone()));
        category.setDescription(categoryDTO.getDescription());

        List<PrinterCfg> printerCfgList = new ArrayList<>();
        if (categoryDTO.getPrinterCfgList() != null) {
            if (!categoryDTO.getPrinterCfgList().isEmpty()) {
                for (PrinterCfgDTO printerCfgDTO : categoryDTO.getPrinterCfgList()) {
                    Optional<PrinterCfg> printerCfgOptional = category.getPrinterCfgs().stream().filter(printerCfg -> {
                        return printerCfg.getID().equals(printerCfgDTO.getID());
                    }).findFirst();

                    PrinterCfg printerCfg;
                    if (printerCfgOptional.isPresent()) {
                        printerCfg = printerCfgOptional.get();
                    } else {
                        printerCfg = printerCfgService.read(printerCfgDTO.getID());
                    }

                    printerCfgList.add(printerCfg);
                }
            }
        }
        category.setPrinterCfgs(printerCfgList);

        category = categoryService.update(category);
        return ResponseEntity.ok(new CategoryDTO(category));
    }

    @ApiOperation("delete category")
    @ApiResponse(code = 200, message = "category deleted", response = CategoryDTO.class)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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

    @ApiOperation("update menu item with new properties by category id and item id")
    @ApiResponse(code = 200, message = "menu item updated", response = ResponseEntity.class)
    @PutMapping("/{id}/menuitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
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

    @ApiOperation("remove one menu item from category")
    @ApiResponse(code = 200, message = "menu item deleted", response = ResponseEntity.class)
    @DeleteMapping("/{id}/menuitem/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
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
