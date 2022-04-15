package business.stats;

import arch.component.LifeCycleStrategy;
import atomic.bean.KeyMapUtils;
import atomic.bean.OrderContent;
import atomic.entity.Order;
import atomic.entity.OrderHistory;
import atomic.entity.Stats;
import atomic.repository.OrderHistoryRepository;
import atomic.repository.OrderRepository;
import atomic.repository.StatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StatsService implements LifeCycleStrategy {

    private final static Logger _LOGGER = LoggerFactory.getLogger(StatsService.class);

    private final StatsRepository statsRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    private static Long statsId;

    public StatsService(StatsRepository statsRepository,
                        OrderRepository orderRepository,
                        OrderHistoryRepository orderHistoryRepository) {
        this.statsRepository = statsRepository;
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public void onBoot() {
        Integer count = statsRepository.countStatsForDate(new Date());
        if (count > 0) {
            _LOGGER.info("Stats record for the day already exist");
        } else {
            _createEmpty();
        }
    }

    @Override
    public void onShutdown() {
        System.out.println("ON SHUTDOWN STATS");
    }

    @SuppressWarnings("all")
    public void loadStatsForDay() {
        Stats record = statsRepository.getById(statsId);
        long orderCount;

        List<Order> orders = orderRepository.findByLoadedFalseAndStatsIdEquals(statsId);
        orderCount = orders != null ? (long) orders.size() : 0L;
        for (Order order : orders) {
            Map<Long, Integer> itemsMap = (Map<Long, Integer>) KeyMapUtils.toMap(record.getItemsCount());
            for (OrderContent content : order.getContent()) {
                Integer itemCount = itemsMap.get(Long.valueOf(content.getItemId()));
                if (itemCount != null)
                    itemsMap.put(Long.valueOf(content.getItemId()), itemCount + 1);
                else
                    itemsMap.put(Long.valueOf(content.getItemId()), 1);
            }

            Map<String, Integer> workersMap = (Map<String, Integer>) KeyMapUtils.toMap(record.getWorkersCount());
            Integer userCount = workersMap.get(order.getCreationUser());
            if (userCount != null)
                workersMap.put(order.getCreationUser(), userCount + 1);
            else
                workersMap.put(order.getCreationUser(), 1);
        }
        Map<Long, Integer> itemsMap = (Map<Long, Integer>) KeyMapUtils.toMap(record.getItemsCount());
        Map<String, Integer> workersMap = (Map<String, Integer>) KeyMapUtils.toMap(record.getWorkersCount());

        List<OrderHistory> ordersHistory = orderHistoryRepository.findByLoadedFalseAndStatsIdEquals(statsId);
        orderCount = orderCount + (ordersHistory != null ? (long) ordersHistory.size() : 0L);
        for (OrderHistory order : ordersHistory) {
            for (OrderContent content : order.getContent()) {
                Integer itemCount = itemsMap.get(Long.valueOf(content.getItemId()));
                if (itemCount != null)
                    itemsMap.put(Long.valueOf(content.getItemId()), itemCount + 1);
                else
                    itemsMap.put(Long.valueOf(content.getItemId()), 1);
            }

            Integer userCount = workersMap.get(order.getCreationUser());
            if (userCount != null)
                workersMap.put(order.getCreationUser(), userCount + 1);
            else
                workersMap.put(order.getCreationUser(), 1);
        }

        record.setItemsCount(KeyMapUtils.toList(itemsMap));
        record.setWorkersCount(KeyMapUtils.toList(workersMap));
        record.setOrdersCount(record.getOrdersCount() + orderCount);
    }

    private void _createEmpty() {
        Stats stats = new Stats();
        stats.setDate(new Date());
        stats.setOrdersCount(0L);
        Stats saved = statsRepository.saveAndFlush(stats);
        statsId = saved.getId();
    }

    public static Long getSessionStatsId() {
        return statsId;
    }
}
