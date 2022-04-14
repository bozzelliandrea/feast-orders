package business.order.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.bean.KeyMap;
import atomic.entity.Order;
import atomic.enums.CategoryProcessingZone;
import business.order.dto.DetailedOrderDTO;
import business.order.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
        dto.setDiscountIds(entity.getDiscount().stream().map(KeyMap::getKey).collect(Collectors.toList()));
        dto.setPaid(entity.getPaid());
        dto.setTakeAway(entity.getTakeAway());
        return dto;
    }

    public DetailedOrderDTO convertEntityDetailed(Order entity) {
        OrderDTO dto = convertEntity(entity);

        DetailedOrderDTO detailedOrderDTO = new DetailedOrderDTO(dto);
        detailedOrderDTO.setNote(entity.getNote());
        detailedOrderDTO.setContent(entity.getContent());
        if (entity.isBarArea()) {
            detailedOrderDTO.addZone(CategoryProcessingZone.BAR.name());
        }
        if (entity.isKitchenArea()) {
            detailedOrderDTO.addZone(CategoryProcessingZone.KITCHEN.name());
        }
        if (entity.isPlateArea()) {
            detailedOrderDTO.addZone(CategoryProcessingZone.PLATE.name());
        }
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
        entity.setPaid(dto.getPaid());
        entity.setTakeAway(dto.getTakeAway());
        return entity;
    }
}
