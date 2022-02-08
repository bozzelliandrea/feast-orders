package be.feastorders.rest;


import be.feastorders.order.dto.OrderStatus;
import be.feastorders.order.entity.type.JsonOrderContentConverter;
import entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ORDER_NEW")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderContent> getContent() {
        return content;
    }

    public void setContent(List<OrderContent> content) {
        this.content = content;
    }

    public boolean isKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(boolean kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public boolean isBarArea() {
        return barArea;
    }

    public void setBarArea(boolean barArea) {
        this.barArea = barArea;
    }

    public boolean isPlateArea() {
        return plateArea;
    }

    public void setPlateArea(boolean plateArea) {
        this.plateArea = plateArea;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public short getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(short tableNumber) {
        this.tableNumber = tableNumber;
    }

    public short getPlaceSettingNumber() {
        return placeSettingNumber;
    }

    public void setPlaceSettingNumber(short placeSettingNumber) {
        this.placeSettingNumber = placeSettingNumber;
    }

    public Boolean getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(Boolean takeAway) {
        this.takeAway = takeAway;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
