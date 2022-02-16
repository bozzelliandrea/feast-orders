package business.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.V2Order;
import business.dto.V2OrderDTO;
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
        dto.setTotal(entity.getTotal());
        return null;
    }

    @Override
    public V2Order convertDTO(V2OrderDTO v2OrderDTO) {
        return null;
    }
}
