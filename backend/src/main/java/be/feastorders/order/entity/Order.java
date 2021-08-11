package be.feastorders.order.entity;

import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.core.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "ORDER_MENU_ITEM",
            joinColumns = @JoinColumn(name = "APP_ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MENU_ITEM_ID")
    )
    private List<MenuItem> menuItems = new ArrayList<>();
}
