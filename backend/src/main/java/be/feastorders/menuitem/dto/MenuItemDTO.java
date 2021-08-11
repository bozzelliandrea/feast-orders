package be.feastorders.menuitem.dto;

import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.core.dto.AbstractDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MenuItemDTO extends AbstractDTO {

    private String name;
    private String description;
    private String color;
    private Float price;

    public MenuItemDTO(MenuItem entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();
        this.price = entity.getPrice();
    }
}
