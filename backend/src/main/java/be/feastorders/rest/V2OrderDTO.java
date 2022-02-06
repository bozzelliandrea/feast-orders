package be.feastorders.rest;

import be.feastorders.core.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class V2OrderDTO extends AbstractDTO {

    private Integer tableNumber;
    private Integer placeSettingNumber;
    private String note;
    private Double total;

}
