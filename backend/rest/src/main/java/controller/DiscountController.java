package controller;

import arch.security.annotation.Admin;
import business.discount.dto.DiscountDTO;
import business.discount.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/discount"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> findAll() {
        return ResponseEntity.ok(discountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.get(id));
    }

    @Admin
    @PostMapping
    public ResponseEntity<DiscountDTO> create(@RequestBody DiscountDTO request) {
        return ResponseEntity.ok(discountService.create(request));
    }

    @Admin
    @PutMapping
    public ResponseEntity<DiscountDTO> update(@RequestBody DiscountDTO request) {
        return ResponseEntity.ok(discountService.update(request));
    }

    @Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<DiscountDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.remove(id));
    }
}
