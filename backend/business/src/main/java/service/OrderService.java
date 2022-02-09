package service;

import dto.OrderDTO;
import dto.OrderItemDetailDTO;
import entity.MenuItem;
import entity.Order;
import entity.OrderItemDetail;
import entity.PrinterCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

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
            detailEntity.getPk().setMenuItemId(menuItem.getID());
            detailEntity.setMenuItemName(menuItem.getName());
            detailEntity.setMenuItemPrice(menuItem.getPrice());
            detailEntity.setMenuItemCategoryId(menuItem.getCategory().getID());

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
        Order entity = super.read(dto.getID());

        if (Objects.nonNull(dto.getClient()))
            entity.setClient(dto.getClient());

        if (Objects.nonNull(dto.getTableNumber()))
            entity.setTableNumber(dto.getTableNumber());

        if (Objects.nonNull(dto.getPlaceSettingNumber()))
            entity.setPlaceSettingNumber(dto.getPlaceSettingNumber());

        if (Objects.nonNull(dto.getCashier()))
            entity.setCashier(dto.getCashier());

        if (Objects.nonNull(dto.getNote()))
            entity.setNote(dto.getNote());

        if (Objects.nonNull(dto.getTotal()))
            entity.setTotal(dto.getTotal());

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
                        orderItemDetail.getPk().setMenuItemId(menuItem.getID());
                        orderItemDetail.setMenuItemName(menuItem.getName());
                        orderItemDetail.setMenuItemPrice(menuItem.getPrice());
                        orderItemDetail.setMenuItemCategoryId(menuItem.getCategory().getID());
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
        Order order = super.read(dto.getID());

        // print orchestration post creation, asynchronous
        Map<PrinterCfg, Order> printerCfgOrderMap = printerAsyncService.splitOrder(order);
        printerAsyncService.executePrintTasks(printerCfgOrderMap);

        return true;
    }
}
