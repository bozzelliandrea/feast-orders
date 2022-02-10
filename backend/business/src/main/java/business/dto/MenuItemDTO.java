package business.dto;

import arch.dto.AbstractDTO;
import atomic.entity.MenuItem;

public class MenuItemDTO extends AbstractDTO {

    private static final long serialVersionUID = -2173740007398550320L;

    private String name;
    private String description;
    private String color;
    private Float price;
    private CategoryDTO category;
    private Long categoryId;

    public MenuItemDTO(MenuItem entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();
        this.price = entity.getPrice();
        this.category = new CategoryDTO(entity.getCategory());
        this.categoryId = entity.getCategory().getID();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public Float getPrice() {
        return price;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}