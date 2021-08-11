package be.feastorders.business.site.dto;

import be.feastorders.business.site.entity.Site;
import be.feastorders.business.core.dto.AbstractDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SiteDTO extends AbstractDTO {

    private String name;
    private String description;
    private String color;

    public SiteDTO(Site entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();
    }
}
