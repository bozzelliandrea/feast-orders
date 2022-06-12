package business.stock.service;

import arch.component.PaginationUtils;
import arch.dto.AbstractPagination;
import atomic.entity.MenuItem;
import atomic.entity.Stock;
import atomic.repository.MenuItemRepository;
import atomic.repository.StockRepository;
import business.menuitem.converter.MenuItemConverter;
import business.stock.converter.StockConverter;
import business.stock.dto.DetailedStockDTO;
import business.stock.dto.StockDTO;
import business.stock.exception.StockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockConverter stockConverter;
    private final StockRepository stockRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemConverter menuItemConverter;

    public StockService(StockConverter stockConverter,
                        StockRepository stockRepository,
                        MenuItemRepository menuItemRepository, MenuItemConverter menuItemConverter) {
        this.stockConverter = stockConverter;
        this.stockRepository = stockRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuItemConverter = menuItemConverter;
    }

    @Transactional
    public StockDTO setStock(StockDTO request) {
        Stock stock = new Stock();

        MenuItem item = menuItemRepository.getById(request.getItemId());

        stock.setQuantity(request.getQty());
        Stock savedStock = stockRepository.saveAndFlush(stock);

        item.setStock(savedStock);
        menuItemRepository.saveAndFlush(item);

        return stockConverter.convertEntity(stockRepository.getById(savedStock.getId()));
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

    public AbstractPagination<DetailedStockDTO> getAllStocks(int page, int size) {
        AbstractPagination<DetailedStockDTO> response = new AbstractPagination<>();

        Pageable paging = PageRequest.of(page, size);

        Page<Stock> stockPage = stockRepository.findAll(paging);
        PaginationUtils.setResponsePagination(stockPage, response);
        response.setData(stockPage.getContent()
                .stream()
                .map(s ->
                        new DetailedStockDTO(menuItemConverter.convertEntity(s.getItem()), s.getQuantity()))
                .collect(Collectors.toList()));

        return response;
    }
}
