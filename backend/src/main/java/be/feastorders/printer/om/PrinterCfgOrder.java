package be.feastorders.printer.om;

import be.feastorders.order.entity.Order;
import be.feastorders.printer.entity.PrinterCfg;

import java.io.Serializable;

public class PrinterCfgOrder implements Serializable {
    private static final long serialVersionUID = 8828263136953437881L;

    private Order order;
    private PrinterCfg printerCfg;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PrinterCfg getPrinterCfg() {
        return printerCfg;
    }

    public void setPrinterCfg(PrinterCfg printerCfg) {
        this.printerCfg = printerCfg;
    }
}
