package business.dto;

import arch.dto.AbstractDTO;

public class V2OrderDTO extends AbstractDTO {

    private Integer tableNumber;
    private Integer placeSettingNumber;
    private String note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
