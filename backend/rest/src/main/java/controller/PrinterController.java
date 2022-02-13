package controller;


import arch.security.annotation.Admin;
import atomic.entity.PrinterCfg;
import atomic.entity.ReportTemplate;
import business.dto.PrinterCfgDTO;
import business.service.PrinterCfgService;
import business.service.PrinterPOCService;
import business.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PrinterPOCService pocService;

    @Autowired
    private PrinterCfgService printerCfgService;

    @Autowired
    private ReportService reportService;

    @ApiOperation("get printers")
    @ApiResponse(code = 200, message = "printers found", response = List.class)
    @GetMapping("/list")
    public ResponseEntity<List<String>> getPrinters() {
        List<String> printers = pocService.getPrinterServices(null, null)
                .stream().map(PrintService::getName).collect(Collectors.toList());
        return ResponseEntity.ok(printers);
    }

    @ApiOperation("get printer configurations")
    @ApiResponse(code = 200, message = "printer configurations found", response = List.class)
    @GetMapping("/cfg")
    public ResponseEntity<List<PrinterCfgDTO>> getPrinterCfgs() {
        List<PrinterCfgDTO> printerCfgs = printerCfgService.findAll().stream()
                .map(PrinterCfgDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(printerCfgs);
    }

    @ApiOperation("create a printer configuration")
    @ApiResponse(code = 200, message = "printer configuration created", response = PrinterCfgDTO.class)
    @PostMapping("/cfg")
    @Admin
    public ResponseEntity<PrinterCfgDTO> createPrinterCfg(@RequestBody PrinterCfgDTO printerCfgDTO) {
        PrinterCfgDTO printerCfg = printerCfgService.savePrinterCfgWithAttrs(printerCfgDTO);
        return ResponseEntity.ok(printerCfg);
    }

    @ApiOperation("retrieve a printer configuration")
    @ApiResponse(code = 200, message = "printer configuration retrieved", response = PrinterCfgDTO.class)
    @GetMapping("/cfg/{id}")
    public ResponseEntity<PrinterCfgDTO> retrievePrinterCfg(@PathVariable Long id) {
        PrinterCfg cfg = printerCfgService.read(id);
        PrinterCfgDTO printerCfgDTO = new PrinterCfgDTO(cfg);
        return ResponseEntity.ok(printerCfgDTO);
    }

    @ApiOperation("update a printer configuration")
    @ApiResponse(code = 200, message = "printer configuration updated", response = PrinterCfgDTO.class)
    @PutMapping("/cfg/{id}")
    @Admin
    public ResponseEntity<PrinterCfgDTO> updatePrinterCfg(@PathVariable Long id, @RequestBody PrinterCfgDTO printerCfgDTO) {
        PrinterCfg cfg = printerCfgService.read(id);
        printerCfgDTO = printerCfgService.updatePrinterCfg(cfg, printerCfgDTO);
        return ResponseEntity.ok(printerCfgDTO);
    }

    @ApiOperation("delete a printer configuration")
    @ApiResponse(code = 200, message = "printer configuration deleted", response = PrinterCfgDTO.class)
    @DeleteMapping("/cfg/{id}")
    @Admin
    public ResponseEntity<PrinterCfgDTO> deletePrinterCfg(@PathVariable Long id) {
        PrinterCfg cfg = printerCfgService.read(id);
        boolean result = printerCfgService.delete(id);
        return ResponseEntity.ok(new PrinterCfgDTO(cfg));
    }

    @ApiOperation("print to a real printer")
    @ApiResponse(code = 200, message = "print done", response = Boolean.class)
    @GetMapping("/print/{printerName}")
    public Boolean print(@PathVariable String printerName, @RequestParam Map<String, String> allParams) {
        return pocService.print(printerName, allParams);
    }

    @ApiOperation("print pdf to a real printer")
    @ApiResponse(code = 200, message = "print pdf done", response = Boolean.class)
    @GetMapping("/printPdf/{printerName}")
    public Boolean printPdf(@PathVariable String printerName, @RequestParam Map<String, String> allParams) {
        return pocService.printPdf(printerName, allParams);
    }

    @ApiOperation("print to a file")
    @ApiResponse(code = 200, message = "print done", response = Boolean.class)
    @GetMapping("/printToFile")
    public Boolean printToFile(@RequestParam Map<String, String> allParams) {
        return pocService.printToFile(allParams);
    }

    @ApiOperation("get all report templates")
    @ApiResponse(code = 200, message = "report templates found", response = List.class)
    @GetMapping("/reportTemplate")
    public ResponseEntity<List<String>> getReportTemplates() {
        List<String> reportTemplates = reportService.getReportTemplates()
                .stream().map(ReportTemplate::getName).collect(Collectors.toList());
        return ResponseEntity.ok(reportTemplates);
    }

}
