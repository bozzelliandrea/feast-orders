package be.feastorders.order;

import be.feastorders.category.service.CategoryService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.menuitem.service.MenuItemService;
import be.feastorders.order.dto.OrderDTO;
import be.feastorders.order.entity.Order;
import be.feastorders.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static be.feastorders.menuitem.service.MenuItemService.menuItemDTO2Entity;
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

    @ApiOperation("Get all menu items in the selected order")
    @ApiResponse(code = 200, message = "return the menu item list", response = ResponseEntity.class)
    @GetMapping("/{id}/menuitem")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItemByOrderId(@PathVariable("id") Long orderID) {

        return ResponseEntity.ok(menuItemService.findAllMenuItemByOrderId(orderID));
    }

    @ApiOperation("Get one menu item by id associated with the selected order")
    @ApiResponse(code = 200, message = "menu item returned", response = ResponseEntity.class)
    @GetMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<MenuItemDTO> getMenuItemByCategoryId(@PathVariable("id") Long orderID,
                                                               @PathVariable("itemId") Long itemID) {


        MenuItemDTO dto = menuItemService.readMenuItemByOrderIdAndItemId(orderID, itemID);

        return ResponseEntity.ok(dto);
    }

    @ApiOperation("create menu item into selected category")
    @ApiResponse(code = 200, message = "list of menu item created", response = ResponseEntity.class)
    @PostMapping("/{id}/menuitem")
    public ResponseEntity<OrderDTO> createMenuItem(@RequestBody List<MenuItemDTO> menuItemDTOList,
                                                   @PathVariable("id") Long orderID) {

        Order order = orderService.read(orderID);
        List<MenuItem> existingItem = order.getMenuItems();


        List<MenuItem> menuItemList = new ArrayList<>();

        for (MenuItemDTO dto : menuItemDTOList) {

            MenuItem entity = menuItemDTO2Entity(dto);

            entity.setCategory(categoryService.read(dto.getCategoryId()));
            entity.getOrders().add(order);

            menuItemList.add(entity);
        }

        order.getMenuItems().clear();
        order.getMenuItems().addAll(existingItem);
        order.getMenuItems().addAll(menuItemList);
        return ResponseEntity.ok(new OrderDTO(orderService.update(order)));

    }

    @ApiOperation("update menu item with new properties by order id and item id")
    @ApiResponse(code = 200, message = "menu item updated", response = ResponseEntity.class)
    @PutMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO,
                                                      @ApiParam(name = "category ID",
                                                              type = "Long",
                                                              allowEmptyValue = false,
                                                              required = true,
                                                              value = "The selected order")
                                                      @PathVariable("id") Long orderID,
                                                      @ApiParam(name = "menu item ID",
                                                              type = "Long",
                                                              required = true,
                                                              value = "The selected menu item to retrieve")
                                                      @PathVariable("itemId") Long itemID) {

        MenuItem oldMenuItem = menuItemService.read(itemID);

        MenuItemService.updateMenuItemEntityProperties(oldMenuItem,
                MenuItemService.menuItemDTO2Entity(menuItemDTO));

        return ResponseEntity.ok(new MenuItemDTO(menuItemService.update(oldMenuItem)));
    }

    @ApiOperation("remove one menu item from order")
    @ApiResponse(code = 200, message = "menu item deleted", response = ResponseEntity.class)
    @DeleteMapping("/{id}/menuitem/{itemId}")
    public ResponseEntity<List<MenuItemDTO>> deleteMenuItemById(
            @ApiParam(name = "category ID",
                    type = "Long",
                    required = true,
                    value = "The selected order")
            @PathVariable("id") Long orderID,
            @ApiParam(name = "menu item ID",
                    type = "Long",
                    required = true,
                    value = "The selected menu item to retrieve")
            @PathVariable("itemId") Long itemID) {

        menuItemService.delete(itemID);

        return ResponseEntity.ok(menuItemService.findAllMenuItemByOrderId(orderID));
    }
}
