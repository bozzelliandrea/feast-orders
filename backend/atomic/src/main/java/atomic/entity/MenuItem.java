package atomic.entity;

import arch.entity.BaseEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_CATEGORY"))
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_STOCK"))
    private Stock stock;

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
