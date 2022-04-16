package business.stats.converter;

import arch.component.AbstractConverter;
import atomic.bean.KeyMapUtils;
import atomic.entity.Stats;
import business.stats.dto.StatsDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StatsConverter extends AbstractConverter<Stats, StatsDTO> {

    @Override
    public StatsDTO convertEntity(Stats entity) {
        StatsDTO dto = new StatsDTO();
        dto.setDate(entity.getDate());
        dto.setOrdersCount(entity.getOrdersCount());
        dto.setItemsMap((Map<Long, Integer>) KeyMapUtils.toMap(entity.getItemsCount()));
        dto.setWorkersMap((Map<String, Integer>) KeyMapUtils.toMap(entity.getWorkersCount()));
        return dto;
    }

    @Override
    public Stats convertDTO(StatsDTO statsDTO) {
        return null;
    }
}
