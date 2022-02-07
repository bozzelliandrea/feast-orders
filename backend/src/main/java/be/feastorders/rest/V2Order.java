package be.feastorders.rest;


import be.feastorders.core.entity.BaseEntity;
import be.feastorders.order.dto.OrderStatus;
import be.feastorders.order.entity.type.JsonOrderContentConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ORDER_NEW")
@Getter
@Setter
public class V2Order extends BaseEntity {


    //TODO: aggiornare con la sequence corretta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "content", columnDefinition = "jsonb")
    @Convert(converter = JsonOrderContentConverter.class)
    private List<OrderContent> content;

    @Column(name = "KITCHEN")
    private boolean kitchenArea;

    @Column(name = "BAR")
    private boolean barArea;

    @Column(name = "PLATE")
    private boolean plateArea;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.TODO;

    @NotNull
    @Column(name = "TOTAL")
    private Double total;

    @NotNull
    @Column(name = "TABLE_NUMBER", nullable = false)
    private short tableNumber;

    @NotNull
    @Column(name = "PLACE_SETTING_NUMBER", nullable = false)
    private short placeSettingNumber;

    @Column(name = "TAKEAWAY")
    private Boolean takeAway;

    @Column(name = "NOTE")
    private String note;
}
