package business.order.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.V2Order;
import business.order.dto.DetailedOrderDTO;
import business.order.dto.V2OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter<V2Order, V2OrderDTO> implements Converter {

    @Override
    public V2OrderDTO convertEntity(V2Order entity) {
        V2OrderDTO dto = new V2OrderDTO();
        convertEntity2DTO(entity, dto);
        dto.setClient(entity.getClient());
        dto.setPlaceSettingNumber((int) entity.getPlaceSettingNumber());
        dto.setTableNumber((int) entity.getTableNumber());
        dto.setTotal(entity.getTotal());
        dto.setDiscount(entity.getDiscount());
        return dto;
    }

    public DetailedOrderDTO convertEntityDetailed(V2Order entity) {
        V2OrderDTO dto = convertEntity(entity);

        DetailedOrderDTO detailedOrderDTO = new DetailedOrderDTO(dto);
        detailedOrderDTO.setNote(entity.getNote());
        detailedOrderDTO.setContent(entity.getContent());
        return detailedOrderDTO;
    }

    @Override
    public V2Order convertDTO(V2OrderDTO dto) {
        V2Order entity = new V2Order();
        convertDTO2BaseEntity(dto, entity);

        if (dto instanceof DetailedOrderDTO) {
            entity.setNote(((DetailedOrderDTO) dto).getNote());
            entity.setContent(((DetailedOrderDTO) dto).getContent());
        }

        entity.setClient(dto.getClient());
        entity.setPlaceSettingNumber(dto.getPlaceSettingNumber().shortValue());
        entity.setTableNumber(dto.getTableNumber().shortValue());
        entity.setTotal(dto.getTotal());
        entity.setDiscount(dto.getDiscount());
        return entity;
    }
}
