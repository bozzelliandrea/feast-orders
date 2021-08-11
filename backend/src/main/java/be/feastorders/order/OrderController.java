package be.feastorders.order;

import be.feastorders.order.dto.OrderDTO;
import be.feastorders.order.entity.Order;
import be.feastorders.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/order"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("Get all saved orders")
    @ApiResponse(code = 200, message = "return a list of orders", response = List.class)
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {

        return ResponseEntity
                .ok(orderService.findAll().stream().map(OrderDTO::new).collect(Collectors.toList()));
    }

    @ApiOperation("Get order by ID")
    @ApiResponse(code = 200, message = "return the selected order", response = OrderDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {

        return Optional.ofNullable(orderService.read(id))
                .map(OrderDTO::new)
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("Add a new order")
    @ApiResponse(code = 200, message = "created the order", response = OrderDTO.class)
    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {

        Order entity = Order.builder()
                .client(dto.getClient())
                .tableNumber(dto.getTableNumber())
                .placeSettingNumber(dto.getPlaceSettingNumber())
                .orderTime(dto.getOrderTime())
                .progressNumber(dto.getProgressNumber())
                .discount(dto.getDiscount())
                .total(dto.getTotal())
                .build();

        return ResponseEntity.ok(new OrderDTO(orderService.create(entity)));
    }

    @ApiOperation("Update an existing order")
    @ApiResponse(code = 200, message = "updated order", response = OrderDTO.class)
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO dto, @PathVariable Long id) {

        if (Objects.isNull(dto.getID())) {
            if (Objects.isNull(id)) {
                ResponseEntity.badRequest().build();
            } else {
                dto.setID(id);
            }
        }

        return ResponseEntity.ok(orderService.updateEntityValues(dto));
    }

    @ApiOperation("Delete an existing order")
    @ApiResponse(code = 200, message = "the delete order result", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (Objects.isNull(id)) {
            ResponseEntity.badRequest().build();
        }

        if (orderService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
