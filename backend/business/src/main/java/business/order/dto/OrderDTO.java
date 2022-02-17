package business.order.dto;

import arch.dto.AbstractDTO;
import atomic.entity.Order;

import java.util.List;

public class OrderDTO extends AbstractDTO {

    private static final long serialVersionUID = 211770738996679270L;

    private String client;
    private Long tableNumber;
    private Long placeSettingNumber;
    private String note;
    private String cashier;
    private Float total;
    private Boolean takeAway = false;
    private List<OrderItemDetailDTO> menuItemList;
    private Boolean printOrder = false;

    public OrderDTO() {

    }

    public OrderDTO(Order entity) {
        super(entity);
        this.client = entity.getClient();
        this.tableNumber = entity.getTableNumber();
        this.placeSettingNumber = entity.getPlaceSettingNumber();
        this.note = entity.getNote();
        this.cashier = entity.getCashier();
        this.total = entity.getTotal();
        this.takeAway = entity.getTakeAway();
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

    public List<OrderItemDetailDTO> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<OrderItemDetailDTO> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public Boolean getPrintOrder() {
        return printOrder;
    }

    public void setPrintOrder(Boolean printOrder) {
        this.printOrder = printOrder;
    }
}
