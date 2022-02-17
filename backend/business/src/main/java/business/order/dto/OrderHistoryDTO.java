package business.order.dto;

import atomic.bean.OrderContent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderHistoryDTO implements Serializable {

    private Long id;
    private List<OrderContent> content;
    private Double total;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderContent> getContent() {
        return content;
    }

    public void setContent(List<OrderContent> content) {
        this.content = content;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
