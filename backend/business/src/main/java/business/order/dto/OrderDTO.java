package business.order.dto;

import arch.dto.AbstractDTO;
import arch.validation.Required;
import arch.validation.RequiredMethod;

public class OrderDTO extends AbstractDTO {

    private static final long serialVersionUID = 4189036589187538080L;

    private Integer tableNumber;
    private Integer placeSettingNumber;
    private String client;
    private Boolean takeAway = false;
    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private Double total;
    private Double paid;
    private Long discountId;

    public OrderDTO() {
    }

    public OrderDTO(OrderDTO dto) {
        super(dto);
        this.tableNumber = dto.getTableNumber();
        this.placeSettingNumber = dto.getPlaceSettingNumber();
        this.client = dto.getClient();
        this.takeAway = dto.getTakeAway();
        this.total = dto.getTotal();
        this.discountId = dto.getDiscountId();
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

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }
}
