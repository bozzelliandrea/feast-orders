package atomic.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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
}
