package entity;

import enums.CategoryProcessingZone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity {

    private static final long serialVersionUID = -373095882708526178L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "CATEGORY_GEN", sequenceName = "CATEGORY_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "CATEGORY_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COLOR")
    private String color;

    @NotNull
    @Column(name = "PROCESSING_ZONE")
    @Enumerated(EnumType.STRING)
    private CategoryProcessingZone processingZone;

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<MenuItem> menuItems = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORY_PRINTERCFG",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRINTERCFG_ID")
    )
    private List<PrinterCfg> printerCfgs = new ArrayList<>();

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CategoryProcessingZone getProcessingZone() {
        return processingZone;
    }

    public void setProcessingZone(CategoryProcessingZone processingZone) {
        this.processingZone = processingZone;
    }

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<PrinterCfg> getPrinterCfgs() {
        return printerCfgs;
    }

    public void setPrinterCfgs(List<PrinterCfg> printerCfgs) {
        this.printerCfgs = printerCfgs;
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
