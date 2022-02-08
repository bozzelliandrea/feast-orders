package be.feastorders.order.entity;

import be.feastorders.order.entity.type.JsonOrderContentConverter;
import be.feastorders.rest.OrderContent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistory {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "content", columnDefinition = "jsonb")
    @Convert(converter = JsonOrderContentConverter.class)
    private List<OrderContent> content;

    @NotNull
    @Column(name = "TOTAL")
    private Double total;

    @NotNull
    @Basic
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
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
