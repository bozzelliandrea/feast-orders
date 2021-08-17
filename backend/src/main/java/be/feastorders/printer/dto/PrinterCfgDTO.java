package be.feastorders.printer.dto;

import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.printer.entity.PrinterCfg;
import be.feastorders.printer.entity.PrinterCfgAttribute;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrinterCfgDTO extends AbstractDTO {

    private static final long serialVersionUID = 3209792370649310652L;

    private String name;
    private String description;
    private String printerName;
    private Map<String, String> attrs = new HashMap<>();

    public PrinterCfgDTO(PrinterCfg entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.printerName = entity.getPrinterName();
        if (entity.getCfgAttrs() != null && !entity.getCfgAttrs().isEmpty()) {
            for (PrinterCfgAttribute cfgAttr : entity.getCfgAttrs()) {
                attrs.put(cfgAttr.getAttr().getName(), cfgAttr.getValue());
            }
        }
    }
}
