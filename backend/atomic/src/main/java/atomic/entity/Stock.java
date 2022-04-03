package atomic.entity;

import arch.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STOCK")
public class Stock extends BaseEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "STOCK_GEN", sequenceName = "STOCK_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "STOCK_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "QUANTITY")
    private Long quantity = 0L;

    @OneToOne(mappedBy = "stock")
    private MenuItem item;

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }
}
