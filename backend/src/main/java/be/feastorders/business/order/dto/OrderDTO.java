package be.feastorders.business.order.dto;

import be.feastorders.business.order.entity.Order;
import be.feastorders.business.core.dto.AbstractDTO;
import lombok.*;

import java.time.ZonedDateTime;

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

    public OrderDTO(Order entity) {
        super(entity);
        this.client = entity.getClient();
        this.tableNumber = entity.getTableNumber();
        this.placeSettingNumber = entity.getPlaceSettingNumber();
        this.orderTime = entity.getOrderTime();
        this.progressNumber = entity.getProgressNumber();
        this.discount = entity.getDiscount();
        this.total = entity.getTotal();
    }
}
