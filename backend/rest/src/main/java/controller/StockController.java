package controller;

import business.stock.dto.StockDTO;
import business.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
