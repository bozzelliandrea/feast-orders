package atomic.entity;

import atomic.bean.KeyMap;
import atomic.type.JsonMapConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STATS")
public class Stats {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "STATS_GEN", sequenceName = "STATS_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "STATS_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DAY_DATE", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "ORDERS_COUNT", nullable = false)
    private Long ordersCount;

    @Column(name = "ITEMS_COUNT", columnDefinition = "jsonb")
    @Convert(converter = JsonMapConverter.class)
    private List<KeyMap> itemsCount;

    @Column(name = "WORKERS_COUNT", columnDefinition = "jsonb")
    @Convert(converter = JsonMapConverter.class)
    private List<KeyMap> workersCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Long ordersCount) {
        this.ordersCount = ordersCount;
    }

    public List<KeyMap> getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(List<KeyMap> itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<KeyMap> getWorkersCount() {
        return workersCount;
    }

    public void setWorkersCount(List<KeyMap> workersCount) {
        this.workersCount = workersCount;
    }
}
