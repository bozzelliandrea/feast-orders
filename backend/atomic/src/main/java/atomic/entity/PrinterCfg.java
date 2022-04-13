package atomic.entity;

import arch.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PRINTERCFG")
public class PrinterCfg extends BaseEntity {

    private static final long serialVersionUID = 7551355451214347516L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PRINTER_CFG_GEN", sequenceName = "PRINTER_CFG_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "PRINTER_CFG_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

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

    public Long getId() {
        return this.id;
    }

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

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public List<PrinterCfgAttribute> getCfgAttrs() {
        return cfgAttrs;
    }

    public void setCfgAttrs(List<PrinterCfgAttribute> cfgAttrs) {
        this.cfgAttrs = cfgAttrs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public ReportTemplate getReportTemplate() {
        return reportTemplate;
    }

    public void setReportTemplate(ReportTemplate reportTemplate) {
        this.reportTemplate = reportTemplate;
    }
}
