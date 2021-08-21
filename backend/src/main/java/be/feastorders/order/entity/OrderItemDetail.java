package be.feastorders.order.entity;

import be.feastorders.menuitem.entity.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

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

    @MapsId("menuItemId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuItemId", referencedColumnName = "ID")
    @JsonIgnore
    private MenuItem menuItem;
}
