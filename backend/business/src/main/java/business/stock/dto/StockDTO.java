package business.stock.dto;

import arch.dto.AbstractDTO;
import arch.validation.Required;
import arch.validation.RequiredMethod;

public class StockDTO extends AbstractDTO {

    private static final long serialVersionUID = -839232809109390281L;

    @Required({RequiredMethod.CREATE})
    private Long itemId;
    private Long qty;

    public StockDTO() {
    }

    public StockDTO(Long itemId, Long qty) {
        this.itemId = itemId;
        this.qty = qty;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
