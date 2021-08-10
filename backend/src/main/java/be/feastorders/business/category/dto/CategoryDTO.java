package be.feastorders.business.category.dto;

import be.feastorders.business.category.entity.Category;
import be.feastorders.core.dto.AbstractDTO;
import lombok.*;

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

    public CategoryDTO(Category entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();
    }
}
