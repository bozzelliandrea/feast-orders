package be.feastorders.business.category.dto;

import be.feastorders.business.category.entity.Category;
import be.feastorders.core.dto.AbstractDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CategoryDTO extends AbstractDTO {

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
