package be.feastorders.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
}
