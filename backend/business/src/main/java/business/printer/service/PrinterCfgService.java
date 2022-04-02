package business.printer.service;

import arch.service.BaseCRUDService;
import atomic.entity.*;
import atomic.repository.PrinterAttrRepository;
import atomic.repository.PrinterCfgRepository;
import atomic.repository.ReportTemplateRepository;
import business.printer.dto.PrinterCfgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PrinterCfgService extends BaseCRUDService<PrinterCfg, Long> {

    private final PrinterCfgRepository cfgRepository;

    @Autowired
    private PrinterAttrRepository attrRepository;

    @Autowired
    private ReportTemplateRepository reportTemplateRepository;

    public PrinterCfgService(PrinterCfgRepository repository) {
        super(repository);
        this.cfgRepository = repository;
    }

    public PrinterCfgDTO savePrinterCfgWithAttrs(PrinterCfgDTO printerCfgDTO) {
        PrinterCfg cfg = printerCfgDTO2Entity(printerCfgDTO);

        // printer attrs
        if (printerCfgDTO.getAttrs() != null && !printerCfgDTO.getAttrs().isEmpty()) {
            for (String key : printerCfgDTO.getAttrs().keySet()) {
                PrinterAttribute attr = attrRepository.getById(key);

                String value = printerCfgDTO.getAttrs().get(key);
                PrinterCfgAttribute printerCfgAttr = new PrinterCfgAttribute();
                printerCfgAttr.setCfg(cfg);
                printerCfgAttr.setAttr(attr);
                printerCfgAttr.setValue(value);

                cfg.getCfgAttrs().add(printerCfgAttr);
                attr.getCfgAttrs().add(printerCfgAttr);
            }
        }

        // printer report template
        String reportTemplateName = printerCfgDTO.getReportTemplate();
        ReportTemplate reportTemplate = reportTemplateRepository.getById(reportTemplateName);
        cfg.setReportTemplate(reportTemplate);

        cfgRepository.save(cfg);

        PrinterCfgDTO dto = new PrinterCfgDTO(cfgRepository.getById(cfg.getID()));
        return dto;
    }

    public PrinterCfgDTO updatePrinterCfg(PrinterCfg oldCfg, PrinterCfgDTO newCfgDTO) {
        oldCfg.setName(newCfgDTO.getName());
        oldCfg.setDescription(newCfgDTO.getDescription());
        oldCfg.setPrinterName(newCfgDTO.getPrinterName());

        // printer attrs
        if (newCfgDTO.getAttrs() != null && !newCfgDTO.getAttrs().isEmpty()) {
            for (String key : newCfgDTO.getAttrs().keySet()) {
                Optional<PrinterCfgAttribute> first = oldCfg.getCfgAttrs().stream()
                        .filter(attr -> attr.getPk().getAttrId().equals(key)).findFirst();
                if (first.isPresent()) {
                    first.get().setValue(newCfgDTO.getAttrs().get(key));
                } else {
                    PrinterAttribute attr = attrRepository.getById(key);

                    PrinterCfgAttribute printerCfgAttr = new PrinterCfgAttribute();
                    printerCfgAttr.setPk(new PrinterCfgAttributePk(oldCfg.getID(), key));
                    printerCfgAttr.setCfg(oldCfg);
                    printerCfgAttr.setAttr(attr);
                    printerCfgAttr.setValue(newCfgDTO.getAttrs().get(key));

                    oldCfg.getCfgAttrs().add(printerCfgAttr);
                }
            }
        }

        // printer report template
        if (!newCfgDTO.getReportTemplate().equalsIgnoreCase(oldCfg.getReportTemplate().getName())) {
            ReportTemplate reportTemplate = reportTemplateRepository.getById(newCfgDTO.getReportTemplate());
            oldCfg.setReportTemplate(reportTemplate);
        }

        cfgRepository.save(oldCfg);

        PrinterCfgDTO dto = new PrinterCfgDTO(cfgRepository.getById(oldCfg.getID()));
        return dto;
    }

    public static PrinterCfg printerCfgDTO2Entity(PrinterCfgDTO dto) {
        Objects.requireNonNull(dto, "DTO param is required.");
        PrinterCfg entity = new PrinterCfg();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrinterName(dto.getPrinterName());
        return entity;
    }
}
