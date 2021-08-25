package be.feastorders.printer.entity;

import be.feastorders.category.entity.Category;
import be.feastorders.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRINTERCFG")
public class PrinterCfg extends BaseEntity {

    private static final long serialVersionUID = 7551355451214347516L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PRINTER_CFG_GEN", sequenceName = "PRINTER_CFG_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "PRINTER_CFG_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRINTER_NAME")
    private String printerName;

    @OneToMany(mappedBy = "cfg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PrinterCfgAttribute> cfgAttrs = new ArrayList<>();

    @ManyToMany(mappedBy = "printerCfgs", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @OneToOne
    private ReportTemplate reportTemplate;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
