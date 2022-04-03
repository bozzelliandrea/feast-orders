package business.order.dto;

import arch.validation.Required;
import arch.validation.RequiredMethod;
import atomic.bean.OrderContent;

import java.util.List;

public class DetailedOrderDTO extends OrderDTO {

    private static final long serialVersionUID = 1716504002734811237L;

    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private List<OrderContent> content;
    private Boolean printOrder = false;
    private String note;

    public DetailedOrderDTO(OrderDTO dto) {
        super(dto);
    }

    public DetailedOrderDTO() {
    }

    public List<OrderContent> getContent() {
        return content;
    }

    public void setContent(List<OrderContent> content) {
        this.content = content;
    }

    public Boolean getPrintOrder() {
        return printOrder;
    }

    public void setPrintOrder(Boolean printOrder) {
        this.printOrder = printOrder;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
