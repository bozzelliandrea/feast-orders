package controller;


import arch.security.annotation.Admin;
import atomic.entity.PrinterCfg;
import atomic.entity.ReportTemplate;
import business.printer.dto.PrinterCfgDTO;
import business.printer.service.PrinterCfgService;
import business.printer.service.PrinterPOCService;
import business.printer.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.PrintService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/printer"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class PrinterController {

    private final PrinterPOCService pocService;
    private final PrinterCfgService printerCfgService;
    private final ReportService reportService;

    public PrinterController(PrinterPOCService pocService, PrinterCfgService printerCfgService, ReportService reportService) {
        this.pocService = pocService;
        this.printerCfgService = printerCfgService;
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getPrinters() {
        List<String> printers = pocService.getPrinterServices(null, null)
                .stream().map(PrintService::getName).collect(Collectors.toList());
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/cfg")
    public ResponseEntity<List<PrinterCfgDTO>> getPrinterCfgs() {
        List<PrinterCfgDTO> printerCfgs = printerCfgService.findAll().stream()
                .map(PrinterCfgDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(printerCfgs);
    }

    @Admin
    @PostMapping("/cfg")
    public ResponseEntity<PrinterCfgDTO> createPrinterCfg(@RequestBody PrinterCfgDTO printerCfgDTO) {
        PrinterCfgDTO printerCfg = printerCfgService.savePrinterCfgWithAttrs(printerCfgDTO);
        return ResponseEntity.ok(printerCfg);
    }

    @GetMapping("/cfg/{id}")
    public ResponseEntity<PrinterCfgDTO> retrievePrinterCfg(@PathVariable Long id) {
        PrinterCfg cfg = printerCfgService.read(id);
        PrinterCfgDTO printerCfgDTO = new PrinterCfgDTO(cfg);
        return ResponseEntity.ok(printerCfgDTO);
    }

    @Admin
    @PutMapping("/cfg/{id}")
    public ResponseEntity<PrinterCfgDTO> updatePrinterCfg(@PathVariable Long id, @RequestBody PrinterCfgDTO printerCfgDTO) {
        PrinterCfg cfg = printerCfgService.read(id);
        printerCfgDTO = printerCfgService.updatePrinterCfg(cfg, printerCfgDTO);
        return ResponseEntity.ok(printerCfgDTO);
    }

    @Admin
    @DeleteMapping("/cfg/{id}")
    public ResponseEntity<PrinterCfgDTO> deletePrinterCfg(@PathVariable Long id) {
        PrinterCfg cfg = printerCfgService.read(id);
        boolean result = printerCfgService.delete(id);
        return ResponseEntity.ok(new PrinterCfgDTO(cfg));
    }

    @GetMapping("/print/{printerName}")
    public Boolean print(@PathVariable String printerName, @RequestParam Map<String, String> allParams) {
        return pocService.print(printerName, allParams);
    }

    @GetMapping("/printPdf/{printerName}")
    public Boolean printPdf(@PathVariable String printerName, @RequestParam Map<String, String> allParams) {
        return pocService.printPdf(printerName, allParams);
    }

    @GetMapping("/printToFile")
    public Boolean printToFile(@RequestParam Map<String, String> allParams) {
        return pocService.printToFile(allParams);
    }

    @GetMapping("/reportTemplate")
    public ResponseEntity<List<String>> getReportTemplates() {
        List<String> reportTemplates = reportService.getReportTemplates()
                .stream().map(ReportTemplate::getName).collect(Collectors.toList());
        return ResponseEntity.ok(reportTemplates);
    }

}
