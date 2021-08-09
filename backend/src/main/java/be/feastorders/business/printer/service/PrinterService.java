package be.feastorders.business.printer.service;

import be.feastorders.business.printer.entity.Printer;
import be.feastorders.business.printer.repository.PrinterRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class PrinterService extends BaseCRUDService<Printer, Long> {

    public PrinterService(PrinterRepository repository) {
        super(repository);
    }
}
