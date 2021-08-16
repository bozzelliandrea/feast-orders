package be.feastorders.printer.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.printer.dto.PrinterCfgDTO;
import be.feastorders.printer.entity.PrinterAttribute;
import be.feastorders.printer.entity.PrinterCfg;
import be.feastorders.printer.entity.PrinterCfgAttribute;
import be.feastorders.printer.repository.PrinterAttrRepository;
import be.feastorders.printer.repository.PrinterCfgAttrRepository;
import be.feastorders.printer.repository.PrinterCfgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PrinterCfgService extends BaseCRUDService<PrinterCfg, Long> {

    private final PrinterCfgRepository cfgRepository;

    @Autowired
    private PrinterAttrRepository attrRepository;

    public PrinterCfgService(PrinterCfgRepository repository) {
        super(repository);
        this.cfgRepository = repository;
    }

    @Transactional
    public PrinterCfgDTO savePrinterCfgWithAttrs(PrinterCfgDTO printerCfgDTO) {
        PrinterCfg cfg = printerCfgDTO2Entity(printerCfgDTO);

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

        cfgRepository.saveAndFlush(cfg);

        PrinterCfgDTO dto = new PrinterCfgDTO(cfgRepository.getById(cfg.getID()));
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
