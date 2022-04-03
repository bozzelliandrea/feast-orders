package business.stock.service;

import atomic.entity.MenuItem;
import atomic.entity.Stock;
import atomic.repository.MenuItemRepository;
import atomic.repository.StockRepository;
import business.stock.converter.StockConverter;
import business.stock.dto.StockDTO;
import business.stock.exception.StockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StockService {

    private final StockConverter stockConverter;
    private final StockRepository stockRepository;
    private final MenuItemRepository menuItemRepository;

    public StockService(StockConverter stockConverter,
                        StockRepository stockRepository,
                        MenuItemRepository menuItemRepository) {
        this.stockConverter = stockConverter;
        this.stockRepository = stockRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Transactional
    public StockDTO setStock(StockDTO request) {
        Stock stock = new Stock();

        MenuItem item = menuItemRepository.getById(request.getItemId());

        stock.setQuantity(request.getQty());
        Stock savedStock = stockRepository.saveAndFlush(stock);

        item.setStock(savedStock);
        menuItemRepository.saveAndFlush(item);

        return stockConverter.convertEntity(stockRepository.getById(savedStock.getID()));
    }

    @Transactional
    public void reduceByMenuItemId(Long itemId, Integer qty) {
        Objects.requireNonNull(itemId);

        Stock stock = stockRepository.getByMenuItemId(itemId);

        long result = stock.getQuantity() - qty.longValue();
        if (result < 0) {
            throw new StockException("Not enough stocks for the selected quantity in item id " + itemId);
        }

        stock.setQuantity(result);
        stockRepository.saveAndFlush(stock);
    }
}
