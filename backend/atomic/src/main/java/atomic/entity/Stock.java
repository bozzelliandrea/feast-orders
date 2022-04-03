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
    private Long quantity;

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
}
