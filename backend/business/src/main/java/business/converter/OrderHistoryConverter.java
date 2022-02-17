package business.converter;

import arch.component.AbstractConverter;
import atomic.entity.OrderHistory;
import business.dto.OrderHistoryDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderHistoryConverter extends AbstractConverter<OrderHistory, OrderHistoryDTO> {

    @Override
    public OrderHistoryDTO convertEntity(OrderHistory entity) {
        OrderHistoryDTO dto = new OrderHistoryDTO();
        dto.setId(entity.getId());
        dto.setTotal(entity.getTotal());
        if (entity.getContent() != null)
            dto.setContent(entity.getContent());
        dto.setDate(entity.getDate());
        return dto;
    }

    @Override
    public OrderHistory convertDTO(OrderHistoryDTO orderHistoryDTO) {
        return null;
    }
}
