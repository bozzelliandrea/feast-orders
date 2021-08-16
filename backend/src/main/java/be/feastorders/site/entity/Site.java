package be.feastorders.site.entity;

import be.feastorders.core.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "SITE")
public class Site extends BaseEntity {

    private static final long serialVersionUID = 3390984960417183536L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SITE_GEN", sequenceName = "SITE_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "SITE_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COLOR")
    private String color;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
