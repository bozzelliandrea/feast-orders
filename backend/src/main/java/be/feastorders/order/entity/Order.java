package be.feastorders.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
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

    @Column(name = "NOTE")
    private String note;

    @Column(name = "CASHIER")
    private String cashier;

    @NotNull
    @Column(name = "TOTAL", nullable = false)
    private Float total;

    @Column(name = "TAKEAWAY")
    private Boolean takeAway;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItemDetail> orderItemDetails;

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Long tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Long getPlaceSettingNumber() {
        return placeSettingNumber;
    }

    public void setPlaceSettingNumber(Long placeSettingNumber) {
        this.placeSettingNumber = placeSettingNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(Boolean takeAway) {
        this.takeAway = takeAway;
    }

    public List<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(List<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    public String getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(String zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Transient
    private String zonedDateTime;
}
