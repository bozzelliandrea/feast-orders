package be.feastorders.order.dto;

import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.order.entity.Order;
import be.feastorders.core.dto.AbstractDTO;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO extends AbstractDTO {

    private static final long serialVersionUID = 211770738996679270L;

    private String client;
    private Long tableNumber;
    private Long placeSettingNumber;
    private ZonedDateTime orderTime;
    private Long progressNumber;
    private Long discount;
    private Float total;
    private List<MenuItemDTO> menuItemList;

    public OrderDTO(Order entity) {
        super(entity);
        this.client = entity.getClient();
        this.tableNumber = entity.getTableNumber();
        this.placeSettingNumber = entity.getPlaceSettingNumber();
        this.orderTime = entity.getOrderTime();
        this.progressNumber = entity.getProgressNumber();
        this.discount = entity.getDiscount();
        this.total = entity.getTotal();
        if (!CollectionUtils.isEmpty(entity.getMenuItems()))
            this.menuItemList = entity.getMenuItems().stream().map(MenuItemDTO::new).collect(Collectors.toList());
    }
}
