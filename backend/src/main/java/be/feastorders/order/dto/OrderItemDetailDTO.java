package be.feastorders.order.dto;

import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.order.entity.OrderItemDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailDTO implements Serializable {

    private static final long serialVersionUID = -7471555587754293139L;

    private Long quantity;
    private Float totalPrice;
    private Long orderId;
    private Long menuItemId;
    private MenuItemDTO menuItem;
    private String note;

    public OrderItemDetailDTO(OrderItemDetail entity) {
        this.quantity = entity.getQuantity();
        this.totalPrice = entity.getTotalPrice();
        this.orderId = entity.getOrder().getID();
        this.menuItem = new MenuItemDTO(entity.getMenuItem());
        this.menuItemId = entity.getMenuItem().getID();
        this.note = entity.getNote();
    }
}
