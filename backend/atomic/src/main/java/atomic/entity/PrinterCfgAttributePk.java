package atomic.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class PrinterCfgAttributePk implements Serializable {
    private static final long serialVersionUID = 8161449427356484522L;

    private Long cfgId;
    private String attrId;

    public PrinterCfgAttributePk() {
    }

    public PrinterCfgAttributePk(Long cfgId, String attrId) {
        this.cfgId = cfgId;
        this.attrId = attrId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        PrinterCfgAttributePk that = (PrinterCfgAttributePk) other;
        return Objects.equals(cfgId, that.cfgId) && Objects.equals(attrId, that.attrId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.cfgId.hashCode();
        hash = hash * prime + this.attrId.hashCode();
        return hash;
    }

    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }
}
