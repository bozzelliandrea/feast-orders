package controller;

import arch.dto.AbstractDTO;
import arch.security.annotation.Admin;
import arch.validation.Required;
import business.dto.OrderDTO;
import business.dto.OrderItemDetailDTO;
import business.service.CategoryService;
import business.service.MenuItemService;
import business.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/order"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("Get all saved orders")
    @ApiResponse(code = 200, message = "return a list of orders", response = List.class)
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {

        return ResponseEntity
                .ok(orderService.findAll().stream().map(OrderDTO::new)
                        .sorted(Comparator.comparing(AbstractDTO::getCreationTimestamp).reversed())
                        .collect(Collectors.toList()));
    }

    @ApiOperation("Get order by ID")
    @ApiResponse(code = 200, message = "return the selected order", response = OrderDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@Required @PathVariable Long id) {

        return Optional.ofNullable(orderService.read(id))
                .map(OrderDTO::new)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("Add a new order")
    @ApiResponse(code = 200, message = "created the order", response = OrderDTO.class)
    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.createEntity(dto));
    }

    @ApiOperation("Update an existing order")
    @ApiResponse(code = 200, message = "updated order", response = OrderDTO.class)
    @PutMapping
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.updateEntityValues(dto));
    }

    @ApiOperation("Delete an existing order")
    @ApiResponse(code = 200, message = "the delete order result", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    @Admin
    public ResponseEntity<?> delete(@PathVariable @Required Long id) {
        return ResponseEntity.ok(orderService.delete(id));
    }

    @ApiOperation("Get all menu items in the selected order")
    @ApiResponse(code = 200, message = "return the menu item list", response = ResponseEntity.class)
    @GetMapping("/{id}/menuitem")
    public ResponseEntity<List<OrderItemDetailDTO>> getAllMenuItemByOrderId(@PathVariable("id") @Required Long orderID) {
        return ResponseEntity.ok(menuItemService.findAllMenuItemByOrderId(orderID));
    }

    @ApiOperation("Print an order")
    @ApiResponse(code = 200, message = "print successful", response = OrderDTO.class)
    @PostMapping("/{id}/print")
    public ResponseEntity<?> print(@RequestBody OrderDTO dto, @Required @PathVariable Long id) {
        return ResponseEntity.ok(orderService.printOrder(dto));
    }
}
