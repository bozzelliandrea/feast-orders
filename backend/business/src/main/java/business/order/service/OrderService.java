package business.order.service;

import arch.service.BaseCRUDService;
import atomic.entity.MenuItem;
import atomic.entity.Order;
import atomic.entity.OrderItemDetail;
import atomic.entity.PrinterCfg;
import atomic.repository.OrderRepository;
import business.menuitem.service.MenuItemService;
import business.order.dto.OrderDTO;
import business.order.dto.OrderItemDetailDTO;
import business.printer.service.PrinterAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService extends BaseCRUDService<Order, Long> {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private PrinterAsyncService printerAsyncService;

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public OrderDTO createEntity(OrderDTO dto) {

        Order entity = new Order();
        //TODO: REFACTOR
//        Order entity = Order.builder()
//                .client(dto.getClient())
//                .tableNumber(dto.getTableNumber())
//                .placeSettingNumber(dto.getPlaceSettingNumber())
//                .note(dto.getNote())
//                .cashier(dto.getCashier())
//                .takeAway(dto.getTakeAway())
//                .total(dto.getTotal())
//                .orderItemDetails(new ArrayList<>())
//                .build();


        for (OrderItemDetailDTO detailDTO : dto.getMenuItemList()) {
            OrderItemDetail detailEntity = new OrderItemDetail();

            detailEntity.setOrder(entity);
            detailEntity.setQuantity(detailDTO.getQuantity());
            detailEntity.setTotalPrice(detailDTO.getTotalPrice());
            MenuItem menuItem = menuItemService.read(detailDTO.getMenuItemId());
            detailEntity.getPk().setMenuItemId(menuItem.getId());
            detailEntity.setMenuItemName(menuItem.getName());
            detailEntity.setMenuItemPrice(menuItem.getPrice());
            detailEntity.setMenuItemCategoryId(menuItem.getCategory().getId());

            entity.getOrderItemDetails().add(detailEntity);
        }

        Order order = super.create(entity);

        if (dto.getPrintOrder()) {
            // print orchestration post creation, asynchronous
            Map<PrinterCfg, Order> printerCfgOrderMap = printerAsyncService.splitOrder(order);
            printerAsyncService.executePrintTasks(printerCfgOrderMap);
        }

        return new OrderDTO(order);
    }

    public OrderDTO updateEntityValues(OrderDTO dto) {
        Order entity = super.read(dto.getId());

        List<OrderItemDetail> orderItemDetailList = new ArrayList<>();
        if (dto.getMenuItemList() != null) {
            if (!dto.getMenuItemList().isEmpty()) {
                for (OrderItemDetailDTO detailDTO : dto.getMenuItemList()) {
                    Optional<OrderItemDetail> orderItemDetailOptional = entity.getOrderItemDetails().stream().filter(orderItemDetail -> {
                        return Objects.equals(orderItemDetail.getPk().getOrderId(), detailDTO.getOrderId()) &&
                                Objects.equals(orderItemDetail.getPk().getMenuItemId(), detailDTO.getMenuItemId());
                    }).findFirst();
                    OrderItemDetail orderItemDetail;
                    if (orderItemDetailOptional.isPresent()) {
                        orderItemDetail = orderItemDetailOptional.get();
                        orderItemDetail.setQuantity(detailDTO.getQuantity());
                        orderItemDetail.setTotalPrice(detailDTO.getTotalPrice());
                        orderItemDetail.setNote(detailDTO.getNote());
                    } else {
                        orderItemDetail = new OrderItemDetail();
                        orderItemDetail.setOrder(entity);
                        orderItemDetail.setQuantity(detailDTO.getQuantity());
                        orderItemDetail.setTotalPrice(detailDTO.getTotalPrice());
                        MenuItem menuItem = menuItemService.read(detailDTO.getMenuItemId());
                        orderItemDetail.getPk().setMenuItemId(menuItem.getId());
                        orderItemDetail.setMenuItemName(menuItem.getName());
                        orderItemDetail.setMenuItemPrice(menuItem.getPrice());
                        orderItemDetail.setMenuItemCategoryId(menuItem.getCategory().getId());
                    }

                    orderItemDetailList.add(orderItemDetail);
                }
            }
        }

        entity.setOrderItemDetails(orderItemDetailList);

        Order order = super.update(entity);

        if (dto.getPrintOrder()) {
            // print orchestration post creation, asynchronous
            Map<PrinterCfg, Order> printerCfgOrderMap = printerAsyncService.splitOrder(order);
            printerAsyncService.executePrintTasks(printerCfgOrderMap);
        }

        return new OrderDTO(order);
    }

    public boolean printOrder(OrderDTO dto) {
        Order order = super.read(dto.getId());

        // print orchestration post creation, asynchronous
        Map<PrinterCfg, Order> printerCfgOrderMap = printerAsyncService.splitOrder(order);
        printerAsyncService.executePrintTasks(printerCfgOrderMap);

        return true;
    }
}
