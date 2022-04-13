package atomic.entity;

import arch.entity.BaseEntity;
import atomic.enums.DiscountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DISCOUNT")
public class Discount extends BaseEntity {

    private static final long serialVersionUID = -4021309879665897886L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "DISCOUNT_GEN", sequenceName = "DISCOUNT_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "DISCOUNT_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private DiscountType type;

    @NotNull
    @Column(name = "VALUE")
    private Double value;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "DISCOUNT_CATEGORY",
            joinColumns = @JoinColumn(name = "DISCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories = new ArrayList<>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
