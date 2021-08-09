package be.feastorders.business.order.entity;

import be.feastorders.core.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "APP_ORDER")
public class Order extends BaseEntity {

    @NotNull
    @Column(name = "CLIENT", nullable = false)
    private String client;

    @NotNull
    @Column(name = "TABLE_NUMBER", nullable = false)
    private Long tableNumber;

    @NotNull
    @Column(name = "PLACE_SETTING_NUMBER", nullable = false)
    private Long placeSettingNumber;

    @NotNull
    @Column(name = "ORDER_TIME", nullable = false)
    private ZonedDateTime orderTime;

    @NotNull
    @Column(name = "PROGRESS_NUMBER", nullable = false)
    private Long progressNumber;

    @NotNull
    @Column(name = "DISCOUNT", nullable = false)
    private Long discount;

    @NotNull
    @Column(name = "TOTAL", nullable = false)
    private Float total;
}
