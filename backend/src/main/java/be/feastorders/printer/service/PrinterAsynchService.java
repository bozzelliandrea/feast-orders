package be.feastorders.printer.service;

import be.feastorders.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class PrinterAsynchService {

    private static int THREAD_POOL_SIZE = 10;

    private ExecutorService executorService;

    public PrinterAsynchService() {
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public List<Order> splitOrder(Order order) {
        // todo: split orders in suborders based on print configuration
        return new ArrayList<>();
    }

    public void executePrintTasks(List<Order> orders) {
        // todo add print orchestration
        for (Order order: orders) {
            Callable<String> printTask = () -> {
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println("Callable task, order: " + order.getID());
                return "Callable task, order: " + order.getID();
            };
            Future<String> future = executorService.submit(printTask);
        }

        return;
    }
}
