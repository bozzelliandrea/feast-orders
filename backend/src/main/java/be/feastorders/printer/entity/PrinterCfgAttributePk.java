package be.feastorders.printer.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class PrinterCfgAttributePk implements Serializable {
    private static final long serialVersionUID = 8161449427356484522L;

    private Long cfgId;
    private String attrId;

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
}
