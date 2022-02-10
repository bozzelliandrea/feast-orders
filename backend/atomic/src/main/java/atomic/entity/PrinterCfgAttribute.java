package atomic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "PRINTERCFGATTRS")
public class PrinterCfgAttribute implements Serializable {
    private static final long serialVersionUID = 7043005913587224150L;

    @EmbeddedId
    private PrinterCfgAttributePk pk = new PrinterCfgAttributePk();
    private String value;

    @MapsId("cfgId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cfgId", referencedColumnName = "ID")
    @JsonIgnore
    private PrinterCfg cfg;

    @MapsId("attrId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attrId", referencedColumnName = "NAME")
    @JsonIgnore
    private PrinterAttribute attr;

    public PrinterCfgAttributePk getPk() {
        return pk;
    }

    public void setPk(PrinterCfgAttributePk pk) {
        this.pk = pk;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PrinterCfg getCfg() {
        return cfg;
    }

    public void setCfg(PrinterCfg cfg) {
        this.cfg = cfg;
    }

    public PrinterAttribute getAttr() {
        return attr;
    }

    public void setAttr(PrinterAttribute attr) {
        this.attr = attr;
    }
}
