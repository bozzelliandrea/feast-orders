package be.feastorders.printer.om;

import be.feastorders.order.entity.Order;
import be.feastorders.printer.entity.PrinterCfg;
import lombok.Data;

import java.io.Serializable;

@Data
public class PrinterCfgOrder implements Serializable {
    private static final long serialVersionUID = 8828263136953437881L;

    private Order order;
    private PrinterCfg printerCfg;
}
