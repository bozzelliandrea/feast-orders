package be.feastorders.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderItemDetailPk implements Serializable {

    private static final long serialVersionUID = -9145023886008815372L;

    private Long orderId;
    private Long menuItemId;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        OrderItemDetailPk that = (OrderItemDetailPk) other;
        return Objects.equals(orderId, that.orderId) && Objects.equals(menuItemId, that.menuItemId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.orderId.hashCode();
        hash = hash * prime + this.menuItemId.hashCode();
        return hash;
    }
}
