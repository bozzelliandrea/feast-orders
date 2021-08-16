package be.feastorders.category.dto;

import be.feastorders.category.entity.Category;
import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.menuitem.dto.MenuItemDTO;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDTO extends AbstractDTO {

    private static final long serialVersionUID = -4011189514121354875L;

    private String name;
    private String description;
    private String color;
    private List<MenuItemDTO> menuItemList;

    public CategoryDTO(Category entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();
    }
}
