package business.order.dto;

import arch.dto.AbstractDTO;
import arch.validation.Required;
import arch.validation.RequiredMethod;

public class V2OrderDTO extends AbstractDTO {

    private Integer tableNumber;
    private Integer placeSettingNumber;
    private String client;
    private Boolean takeAway = false;
    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private Double total;
    private Integer discount;

    public V2OrderDTO() {
    }

    public V2OrderDTO(V2OrderDTO dto) {
        super(dto);
        this.tableNumber = dto.getTableNumber();
        this.placeSettingNumber = dto.getPlaceSettingNumber();
        this.client = dto.getClient();
        this.takeAway = dto.getTakeAway();
        this.total = dto.getTotal();
        this.discount = dto.getDiscount();
    }

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
