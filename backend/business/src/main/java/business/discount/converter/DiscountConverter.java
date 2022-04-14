package business.discount.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.Discount;
import atomic.enums.DiscountType;
import business.discount.dto.DiscountDTO;
import org.springframework.stereotype.Component;

@Component
public class DiscountConverter extends AbstractConverter<Discount, DiscountDTO> implements Converter {

    @Override
    public DiscountDTO convertEntity(Discount entity) {
        DiscountDTO dto = new DiscountDTO();
        convertEntity2DTO(entity, dto);
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setType(entity.getType().toString());
        dto.setValue(entity.getValue());
        return dto;
    }

    @Override
    public Discount convertDTO(DiscountDTO dto) {
        Discount entity = new Discount();
        convertDTO2BaseEntity(dto, entity);
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setType(DiscountType.valueOf(dto.getType()));
        entity.setValue(dto.getValue());
        return entity;
    }
}
