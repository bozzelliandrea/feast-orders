package be.feastorders.order.dto;

import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.order.entity.Order;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
