package atomic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRINTERATTR")
public class PrinterAttribute implements Serializable {

    private static final long serialVersionUID = -215132403521063058L;

    @Id
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    private String type;

    @OneToMany(mappedBy = "attr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PrinterCfgAttribute> cfgAttrs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PrinterCfgAttribute> getCfgAttrs() {
        return cfgAttrs;
    }

    public void setCfgAttrs(List<PrinterCfgAttribute> cfgAttrs) {
        this.cfgAttrs = cfgAttrs;
    }
}

