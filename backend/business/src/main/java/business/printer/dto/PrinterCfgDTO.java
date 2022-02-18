package business.printer.dto;

import arch.dto.AbstractDTO;
import atomic.entity.PrinterCfg;
import atomic.entity.PrinterCfgAttribute;

import java.util.HashMap;
import java.util.Map;

public class PrinterCfgDTO extends AbstractDTO {

    private static final long serialVersionUID = 3209792370649310652L;

    private String name;
    private String description;
    private String printerName;
    private Map<String, String> attrs = new HashMap<>();
    private String reportTemplate;

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
        if (entity.getReportTemplate() != null) {
            this.reportTemplate = entity.getReportTemplate().getName();
        }
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

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public String getReportTemplate() {
        return reportTemplate;
    }

    public void setReportTemplate(String reportTemplate) {
        this.reportTemplate = reportTemplate;
    }
}
