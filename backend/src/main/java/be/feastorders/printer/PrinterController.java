package be.feastorders.printer;

import be.feastorders.printer.dto.PrinterCfgDTO;
import be.feastorders.printer.service.PrinterCfgService;
import be.feastorders.printer.service.PrinterPOCService;
import be.feastorders.printer.service.ReportService;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.PrintService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/printer"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class PrinterController {

    // todo add printer cfg endpoints

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
    @ApiResponse(code = 200, message = "printer configuration created", response = List.class)
    @PostMapping("/cfg")
    public ResponseEntity<PrinterCfgDTO> createPrinterCfg(@RequestBody PrinterCfgDTO printerCfgDTO) {
        PrinterCfgDTO printerCfg = printerCfgService.savePrinterCfgWithAttrs(printerCfgDTO);
        return ResponseEntity.ok(printerCfg);
    }

    @ApiOperation("print to a real printer")
    @ApiResponse(code = 200, message = "print done", response = Boolean.class)
    @GetMapping("/print/{printerName}")
//    @RequestParam(value = "copies", required = false)  Integer copies,
//    @RequestParam(value = "mediaSize", required = false) String mediaSize,
//    @RequestParam(value = "orientation", required = false, defaultValue = "portrait") String orientation,
//    @RequestParam(value = "sides", required = false, defaultValue = "one-sided") String sides,
//    @RequestParam(value = "color", required = false) Boolean color
    public Boolean print(@PathVariable String printerName, @RequestParam Map<String,String> allParams) {
        return pocService.print(printerName, allParams);
    }

    @ApiOperation("print pdf to a real printer")
    @ApiResponse(code = 200, message = "print pdf done", response = Boolean.class)
    @GetMapping("/printPdf/{printerName}")
//    @RequestParam(value = "copies", required = false)  Integer copies,
//    @RequestParam(value = "mediaSize", required = false) String mediaSize,
//    @RequestParam(value = "orientation", required = false, defaultValue = "portrait") String orientation,
//    @RequestParam(value = "sides", required = false, defaultValue = "one-sided") String sides,
//    @RequestParam(value = "color", required = false) Boolean color
    public Boolean printPdf(@PathVariable String printerName, @RequestParam Map<String,String> allParams) {
        return pocService.printPdf(printerName, allParams);
    }

    @ApiOperation("print to a file")
    @ApiResponse(code = 200, message = "print done", response = Boolean.class)
    @GetMapping("/printToFile")
    public Boolean printToFile(@RequestParam Map<String,String> allParams) {
        return pocService.printToFile(allParams);
    }

    @ApiOperation("generate PDF using template engine")
    @ApiResponse(code = 200, message = "pdf generated", response = Boolean.class)
    @GetMapping("/pdf")
    public Boolean createPdf() {
        String html = reportService.parseThymeleafTemplate();
        try {
            reportService.generatePdfFromHtml(html);
            return true;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
