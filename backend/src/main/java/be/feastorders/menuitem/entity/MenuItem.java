package be.feastorders.menuitem.entity;

import be.feastorders.category.entity.Category;
import be.feastorders.core.entity.BaseEntity;
import be.feastorders.order.entity.Order;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "MENU_ITEM")
public class MenuItem extends BaseEntity {

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COLOR")
    private String color;

    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private List<Order> orders;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id",
            nullable = false)
    private Category category;
}
