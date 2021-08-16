package be.feastorders.printer.entity;

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
@Table(name = "PRINTER")
public class Printer extends BaseEntity {

    private static final long serialVersionUID = 7551355451214347516L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PRINTER_GEN", sequenceName = "PRINTER_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "PRINTER_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PAPER_FORMAT", nullable = false)
    private String paperFormat;

    @NotNull
    @Column(name = "COPIES_NUMBER", nullable = false)
    private String copiesNumber;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
