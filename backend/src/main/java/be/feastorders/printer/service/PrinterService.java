package be.feastorders.printer.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.printer.entity.Printer;
import be.feastorders.printer.repository.PrinterRepository;
import org.springframework.stereotype.Service;

@Service
public class PrinterService extends BaseCRUDService<Printer, Long> {

    public PrinterService(PrinterRepository repository) {
        super(repository);
    }
}
