package business.order.dto;

import arch.dto.AbstractDTO;

public class V2OrderDTO extends AbstractDTO {

    private Integer tableNumber;
    private Integer placeSettingNumber;
    private String client;
    private Boolean takeAway = false;
    private Double total;

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getPlaceSettingNumber() {
        return placeSettingNumber;
    }

    public void setPlaceSettingNumber(Integer placeSettingNumber) {
        this.placeSettingNumber = placeSettingNumber;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Boolean getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(Boolean takeAway) {
        this.takeAway = takeAway;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
