package controller;

import arch.dto.AbstractPagination;
import business.stock.dto.DetailedStockDTO;
import business.stock.dto.StockDTO;
import business.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static arch.component.PaginationUtils.DEFAULT_PAGE_SIZE;
import static arch.component.PaginationUtils.DEFAULT_PAGE_START;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/stock", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StockDTO> setStock(@RequestBody StockDTO request) {
        return ResponseEntity.ok(stockService.setStock(request));
    }

    @GetMapping
    public ResponseEntity<AbstractPagination<DetailedStockDTO>> getAllStocks(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_START, required = false) int page,
                                                                             @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) int size) {
        return ResponseEntity.ok(stockService.getAllStocks(page, size));
    }
}
