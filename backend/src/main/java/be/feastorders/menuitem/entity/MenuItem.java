package be.feastorders.menuitem.entity;

import be.feastorders.category.entity.Category;
import be.feastorders.core.entity.BaseEntity;
import be.feastorders.order.entity.Order;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MENU_ITEM")
public class MenuItem extends BaseEntity {

    private static final long serialVersionUID = 8433567219729804440L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "MENU_ITEM_GEN", sequenceName = "MENU_ITEM_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "MENU_ITEM_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

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
    private List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_CATEGORY"))
    private Category category;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
