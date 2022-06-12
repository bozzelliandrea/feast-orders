package business.stock.converter;

import arch.component.AbstractConverter;
import arch.component.Converter;
import atomic.entity.MenuItem;
import atomic.entity.Stock;
import business.stock.dto.StockDTO;
import org.springframework.stereotype.Component;

@Component
public class StockConverter extends AbstractConverter<Stock, StockDTO> implements Converter {

    @Override
    public StockDTO convertEntity(Stock entity) {
        StockDTO dto = new StockDTO();
        convertEntity2DTO(entity, dto);
        dto.setQty(entity.getQuantity());
        dto.setItemId(entity.getId());
        return dto;
    }

    @Override
    public Stock convertDTO(StockDTO dto) {
        Stock stock = new Stock();
        stock.setQuantity(dto.getQty());
        convertDTO2BaseEntity(dto, stock);
        stock.setItem(new MenuItem());
        stock.getItem().setId(dto.getItemId());
        return stock;
    }
}
