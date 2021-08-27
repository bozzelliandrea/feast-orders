package be.feastorders.order.dto;

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

    private Long orderId;
    private Long menuItemId;
    private String menuItemName;
    private Float menuItemPrice;
    private Long menuItemCategoryId;
    private Long quantity;
    private Float totalPrice;
    private String note;

    public OrderItemDetailDTO(OrderItemDetail entity) {
        this.quantity = entity.getQuantity();
        this.totalPrice = entity.getTotalPrice();
        this.orderId = entity.getOrder().getID();
        this.menuItemId = entity.getPk().getMenuItemId();
        this.menuItemName = entity.getMenuItemName();
        this.menuItemPrice = entity.getMenuItemPrice();
        this.menuItemCategoryId = entity.getMenuItemCategoryId();
        this.note = entity.getNote();
    }
}
