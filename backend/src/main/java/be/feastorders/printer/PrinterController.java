package be.feastorders.printer;

import be.feastorders.printer.service.PrinterPOCService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.PrintService;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/printer"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class PrinterController {

    @Autowired
    private PrinterPOCService pocService;

    @ApiOperation("get printers")
    @ApiResponse(code = 200, message = "printers found", response = List.class)
    @PostMapping("/list")
    public List<String> getPrinters() {
        List<String> printers = pocService.getPrinterServices(null, null)
                .stream().map(PrintService::getName).collect(Collectors.toList());
        return printers;
    }

    @ApiOperation("print")
    @ApiResponse(code = 200, message = "print done", response = Boolean.class)
    @PostMapping("/print")
    public Boolean print() {
        return pocService.print();
    }

}
