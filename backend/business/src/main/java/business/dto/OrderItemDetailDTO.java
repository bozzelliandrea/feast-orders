package business.dto;

import atomic.entity.OrderItemDetail;

import java.io.Serializable;

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

    public OrderItemDetailDTO() {
    }

    public OrderItemDetailDTO(OrderItemDetail entity) {
        this.quantity = entity.getQuantity();
        this.totalPrice = entity.getTotalPrice();
        this.orderId = entity.getOrder().getId();
        this.menuItemId = entity.getPk().getMenuItemId();
        this.menuItemName = entity.getMenuItemName();
        this.menuItemPrice = entity.getMenuItemPrice();
        this.menuItemCategoryId = entity.getMenuItemCategoryId();
        this.note = entity.getNote();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Float getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(Float menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public Long getMenuItemCategoryId() {
        return menuItemCategoryId;
    }

    public void setMenuItemCategoryId(Long menuItemCategoryId) {
        this.menuItemCategoryId = menuItemCategoryId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
