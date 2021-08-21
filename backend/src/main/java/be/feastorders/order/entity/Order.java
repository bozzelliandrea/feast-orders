package be.feastorders.order.entity;

import be.feastorders.core.entity.BaseEntity;
import be.feastorders.printer.entity.PrinterCfgAttribute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "APP_ORDER")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 419715887508011992L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "APP_ORDER_GEN", sequenceName = "APP_ORDER_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "APP_ORDER_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "CLIENT", nullable = false)
    private String client;

    @NotNull
    @Column(name = "TABLE_NUMBER", nullable = false)
    private Long tableNumber;

    @NotNull
    @Column(name = "PLACE_SETTING_NUMBER", nullable = false)
    private Long placeSettingNumber;

    @NotNull
    @Column(name = "PROGRESS_NUMBER", nullable = false)
    private Long progressNumber;

    @NotNull
    @Column(name = "DISCOUNT", nullable = false)
    private Long discount;

    @NotNull
    @Column(name = "TOTAL", nullable = false)
    private Float total;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItemDetail> orderItemDetails;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
