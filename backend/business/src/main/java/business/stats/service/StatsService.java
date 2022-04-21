package business.stats.service;

import arch.component.DateUtils;
import arch.component.LifeCycleStrategy;
import atomic.bean.KeyMapUtils;
import atomic.bean.OrderContent;
import atomic.entity.Order;
import atomic.entity.OrderHistory;
import atomic.entity.Stats;
import atomic.repository.OrderHistoryRepository;
import atomic.repository.OrderRepository;
import atomic.repository.StatsRepository;
import business.stats.converter.StatsConverter;
import business.stats.dto.StatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatsService implements LifeCycleStrategy {

    private final static Logger _LOGGER = LoggerFactory.getLogger(StatsService.class);

    private final StatsRepository statsRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final StatsConverter statsConverter;

    private static Long statsId;

    public StatsService(StatsRepository statsRepository,
                        OrderRepository orderRepository,
                        OrderHistoryRepository orderHistoryRepository,
                        StatsConverter statsConverter) {
        this.statsRepository = statsRepository;
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.statsConverter = statsConverter;
    }

    @Override
    public void onBoot() {
        Integer count = statsRepository.countStatsForDate(new Date());
        if (count > 0) {
            _LOGGER.info("Stats record for the day already exist");
            Stats stats = statsRepository.findByDate(new Date());
            if (stats != null) {
                statsId = stats.getId();
            }
        } else {
            _createEmpty();
        }
    }

    @Override
    public void onShutdown() {
        loadStatsForDay();
        _LOGGER.info("Loaded all stats for the day");
    }

    public List<StatsDTO> getStatsByDateRange(String startDate, String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(Objects.requireNonNullElseGet(startDate, () -> new Date().toString()));
        Date end = format.parse(Objects.requireNonNullElseGet(endDate, () -> new Date().toString()));

        if (DateUtils.isToday(start) || DateUtils.isToday(end)) {
            loadStatsForDay();
        }

        List<Stats> stats = statsRepository.findByDateGreaterThanEqualAndDateLessThanEqual(start, end);

        return stats.stream().map(statsConverter::convertEntity).collect(Collectors.toList());
    }

    @SuppressWarnings("all")
    public synchronized void loadStatsForDay() {
        Stats record = statsRepository.getById(statsId);
        Map<Long, Integer> itemsMap = (Map<Long, Integer>) KeyMapUtils.toMap(record.getItemsCount());
        Map<String, Integer> workersMap = (Map<String, Integer>) KeyMapUtils.toMap(record.getWorkersCount());

        long orderCount;

        List<Order> orders = orderRepository.findByLoadedFalseAndStatsIdEquals(statsId);
        orderCount = orders != null ? (long) orders.size() : 0L;

        _loadOrderStats(orders, itemsMap, workersMap);

        List<OrderHistory> ordersHistory = orderHistoryRepository.findByLoadedFalseAndStatsIdEquals(statsId);
        orderCount = orderCount + (ordersHistory != null ? (long) ordersHistory.size() : 0L);

        _loadOrderHistoryStats(ordersHistory, itemsMap, workersMap);

        record.setItemsCount(KeyMapUtils.toList(itemsMap));
        record.setWorkersCount(KeyMapUtils.toList(workersMap));
        record.setOrdersCount(record.getOrdersCount() + orderCount);
        statsRepository.saveAndFlush(record);
    }

    private void _loadOrderHistoryStats(List<OrderHistory> ordersHistoryList, Map<Long, Integer> itemsMap, Map<String, Integer> workersMap) {
        for (OrderHistory order : ordersHistoryList) {
            for (OrderContent content : order.getContent()) {
                itemsMap.merge(Long.valueOf(content.getItemId()), 1, Integer::sum);
            }

            workersMap.merge(order.getCreationUser(), 1, Integer::sum);

            order.setLoaded(true);
            orderHistoryRepository.saveAndFlush(order);
        }
    }

    private void _loadOrderStats(List<Order> orderList, Map<Long, Integer> itemsMap, Map<String, Integer> workersMap) {
        for (Order order : orderList) {
            for (OrderContent content : order.getContent()) {
                itemsMap.merge(Long.valueOf(content.getItemId()), 1, Integer::sum);
            }

            workersMap.merge(order.getCreationUser(), 1, Integer::sum);

            order.setLoaded(true);
            orderRepository.saveAndFlush(order);
        }

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
