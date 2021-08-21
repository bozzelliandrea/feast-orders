package be.feastorders.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailDTO implements Serializable {

    private static final long serialVersionUID = -7471555587754293139L;

    private Long quantity;
    private Float totalPrice;
    private Long orderId;
    private Long menuItemId;
    private String note;
}
