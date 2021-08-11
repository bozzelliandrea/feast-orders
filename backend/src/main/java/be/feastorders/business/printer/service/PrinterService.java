package be.feastorders.business.printer.service;

import be.feastorders.business.core.service.BaseCRUDService;
import be.feastorders.business.printer.entity.Printer;
import be.feastorders.business.printer.repository.PrinterRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(3)
public class PrinterService extends BaseCRUDService<Printer, Long> {

    public PrinterService(PrinterRepository repository) {
        super(repository);
    }
}
