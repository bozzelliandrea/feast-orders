package business.order.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.Order;
import business.order.dto.DetailedOrderDTO;
import business.order.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends AbstractConverter<Order, OrderDTO> implements Converter {

    @Override
    public OrderDTO convertEntity(Order entity) {
        OrderDTO dto = new OrderDTO();
        convertEntity2DTO(entity, dto);
        dto.setClient(entity.getClient());
        dto.setPlaceSettingNumber((int) entity.getPlaceSettingNumber());
        dto.setTableNumber((int) entity.getTableNumber());
        dto.setTotal(entity.getTotal());
        dto.setDiscount(entity.getDiscount());
        return dto;
    }

    public DetailedOrderDTO convertEntityDetailed(Order entity) {
        OrderDTO dto = convertEntity(entity);

        DetailedOrderDTO detailedOrderDTO = new DetailedOrderDTO(dto);
        detailedOrderDTO.setNote(entity.getNote());
        detailedOrderDTO.setContent(entity.getContent());
        return detailedOrderDTO;
    }

    @Override
    public Order convertDTO(OrderDTO dto) {
        Order entity = new Order();
        convertDTO2BaseEntity(dto, entity);

        if (dto instanceof DetailedOrderDTO) {
            entity.setNote(((DetailedOrderDTO) dto).getNote());
            entity.setContent(((DetailedOrderDTO) dto).getContent());
        }

        entity.setClient(dto.getClient());
        entity.setPlaceSettingNumber(dto.getPlaceSettingNumber() != null ? dto.getPlaceSettingNumber().shortValue() : 0);
        entity.setTableNumber(dto.getTableNumber() != null ? dto.getTableNumber().shortValue() : 0);
        entity.setTotal(dto.getTotal());
        entity.setDiscount(dto.getDiscount());
        return entity;
    }
}
