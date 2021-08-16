package be.feastorders.printer;

import be.feastorders.printer.service.PrinterPOCService;
import be.feastorders.printer.service.ReportService;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PrinterPOCService pocService;

    @Autowired
    private ReportService reportService;

    @ApiOperation("get printers")
    @ApiResponse(code = 200, message = "printers found", response = List.class)
    @GetMapping("/list")
    public List<String> getPrinters() {
        List<String> printers = pocService.getPrinterServices(null, null)
                .stream().map(PrintService::getName).collect(Collectors.toList());
        return printers;
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
