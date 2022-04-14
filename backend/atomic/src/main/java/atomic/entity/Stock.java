package atomic.entity;

import arch.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STOCK")
public class Stock extends BaseEntity {

    private static final long serialVersionUID = -4762068878983435687L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "STOCK_GEN", sequenceName = "STOCK_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "STOCK_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "QUANTITY")
    private Long quantity = 0L;

    @OneToOne(mappedBy = "stock")
    private MenuItem item;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
