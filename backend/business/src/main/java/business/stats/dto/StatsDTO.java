package business.stats.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class StatsDTO implements Serializable {

    private static final long serialVersionUID = -1869205763784759001L;

    private Long ordersCount;
    private Date date;
    private Map<Long, Integer> itemsMap;
    private Map<String, Integer> workersMap;

    public Long getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Long ordersCount) {
        this.ordersCount = ordersCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Long, Integer> getItemsMap() {
        return itemsMap;
    }

    public void setItemsMap(Map<Long, Integer> itemsMap) {
        this.itemsMap = itemsMap;
    }

    public Map<String, Integer> getWorkersMap() {
        return workersMap;
    }

    public void setWorkersMap(Map<String, Integer> workersMap) {
        this.workersMap = workersMap;
    }
}
