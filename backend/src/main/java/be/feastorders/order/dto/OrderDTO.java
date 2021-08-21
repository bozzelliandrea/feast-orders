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
    private ZonedDateTime orderTime;
    private Long progressNumber;
    private Long discount;
    private Float total;
    private List<OrderItemDetailDTO> menuItemList;
    private Boolean printOrder;

    public OrderDTO(Order entity) {
        super(entity);
        this.client = entity.getClient();
        this.tableNumber = entity.getTableNumber();
        this.placeSettingNumber = entity.getPlaceSettingNumber();
        this.progressNumber = entity.getProgressNumber();
        this.discount = entity.getDiscount();
        this.total = entity.getTotal();
    }
}
