package be.feastorders.business.category;

import be.feastorders.business.category.dto.CategoryDTO;
import be.feastorders.business.category.entity.Category;
import be.feastorders.business.category.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        List<CategoryDTO> categories = service.find().stream().map(CategoryDTO::new).collect(Collectors.toList());
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
    public CategoryDTO delete(@PathVariable Long id) {
        Category category = service.read(id);

        boolean result = service.delete(id);
        return new CategoryDTO(category);
    }
}
