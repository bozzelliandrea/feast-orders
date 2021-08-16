package be.feastorders.printer.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private PrinterCfg cfg;

    @MapsId("attrId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attrId", referencedColumnName = "NAME")
    private PrinterAttribute attr;
}
