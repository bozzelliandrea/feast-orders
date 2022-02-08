package be.feastorders.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDERITEM")
public class OrderItemDetail implements Serializable {

    private static final long serialVersionUID = 5613052948966122875L;

    @EmbeddedId
    private OrderItemDetailPk pk = new OrderItemDetailPk();

    @NotNull
    @Column(name = "MENU_ITEM_NAME", nullable = false)
    private String menuItemName;

    @NotNull
    @Column(name = "MENU_ITEM_PRICE", nullable = false)
    private Float menuItemPrice;

    @Column(name = "MENU_ITEM_CAT_ID")
    private Long menuItemCategoryId;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @NotNull
    @Column(name = "TOTAL_PRICE", nullable = false)
    private Float totalPrice;

    @Column(name = "NOTE")
    private String note;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "ID")
    @JsonIgnore
    private Order order;

    public OrderItemDetailPk getPk() {
        return pk;
    }

    public void setPk(OrderItemDetailPk pk) {
        this.pk = pk;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
