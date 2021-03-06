package business.printer.service;

import atomic.bean.OrderContent;
import atomic.entity.Category;
import atomic.entity.Order;
import atomic.entity.PrinterCfg;
import atomic.entity.PrinterCfgAttribute;
import business.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class PrinterAsyncService {

    private final static int THREAD_POOL_SIZE = 10;
    private final ExecutorService executorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PrinterPOCService printerService;

    public PrinterAsyncService() {
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public Map<PrinterCfg, Order> splitOrder(Order order) {
        // split orders in suborders based on print configurations
        Map<PrinterCfg, Order> printerCfgOrderMap = new HashMap<>();

        for (OrderContent detail : order.getContent()) {
            Long categoryId = detail.getCategoryId();
            Category category = categoryService.read(categoryId);

            for (PrinterCfg printerCfg : category.getPrinterCfgs()) {
                if (!printerCfgOrderMap.containsKey(printerCfg)) {
                    Order subOrder = new Order();
                    subOrder.setId(order.getId());
                    subOrder.setTotal(order.getTotal());
                    subOrder.setTableNumber(order.getTableNumber());
                    subOrder.setClient(order.getClient());
                    subOrder.setPlaceSettingNumber(order.getPlaceSettingNumber());
                    subOrder.setNote(order.getNote() != null ? order.getNote() : "");
                    subOrder.setTakeAway(order.getTakeAway());
                    subOrder.setCreationTimestamp(order.getCreationTimestamp());
                    List<OrderContent> details = new ArrayList<>();
                    details.add(detail);
                    subOrder.setContent(details);

                    printerCfgOrderMap.put(printerCfg, subOrder);
                } else {
                    // update the entry details
                    Order subOrder = printerCfgOrderMap.get(printerCfg);
                    subOrder.getContent().add(detail);

                    printerCfgOrderMap.replace(printerCfg, subOrder);
                }
            }
        }

        return printerCfgOrderMap;
    }

    public void executePrintTasks(Map<PrinterCfg, Order> printerCfgOrderMap) {
        for (PrinterCfg printerCfg: printerCfgOrderMap.keySet()) {
            Callable<String> printTask = () -> {
                TimeUnit.MILLISECONDS.sleep(1000);
                Order order = printerCfgOrderMap.get(printerCfg);
                String orderPdfFilePath = reportService.createPdf(order, printerCfg);
                System.out.println("Callable task, order: " + order.getId() + ", created PDF for " + printerCfg.getReportTemplate().getName());

                Map<String, String> printerAttrs = new HashMap<>();
                printerAttrs.put("filename", orderPdfFilePath);
                for (PrinterCfgAttribute attr: printerCfg.getCfgAttrs()) {
                    String key = attr.getAttr().getName();
                    String value = attr.getValue();
                    printerAttrs.put(key, value);
                }
                boolean result = printerService.printPdf(printerCfg.getPrinterName(), printerAttrs);
                System.out.println("Callable task, order: " + order.getId() + ", printed PDF for " + printerCfg.getReportTemplate().getName());

                return "Callable task, order: " + order.getId() + ", printed: " + result;
            };

            Future<String> future = executorService.submit(printTask);
        }
    }
}
