package be.feastorders.category.entity;

import be.feastorders.core.entity.BaseEntity;
import be.feastorders.menuitem.entity.MenuItem;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<MenuItem> menuItems = new HashSet<>();

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
