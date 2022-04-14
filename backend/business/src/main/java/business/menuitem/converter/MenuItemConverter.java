package business.menuitem.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.MenuItem;
import business.menuitem.dto.MenuItemDTO;
import org.springframework.stereotype.Component;

@Component
public class MenuItemConverter extends AbstractConverter<MenuItem, MenuItemDTO> implements Converter {

    @Override
    public MenuItemDTO convertEntity(MenuItem entity) {
        MenuItemDTO dto = new MenuItemDTO();
        convertEntity2DTO(entity, dto);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setColor(entity.getColor());
        dto.setPrice(entity.getPrice());
        dto.setCategoryId(entity.getCategory().getId());
        return dto;
    }

    @Override
    public MenuItem convertDTO(MenuItemDTO menuItemDTO) {
        return null;
    }
}
