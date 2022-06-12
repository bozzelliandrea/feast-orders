package business.stock.dto;

import business.menuitem.dto.MenuItemDTO;

public class DetailedStockDTO extends StockDTO {

    private final MenuItemDTO item;

    public DetailedStockDTO(MenuItemDTO item, Long qty) {
        super(item.getId(), qty);
        this.item = item;
    }

    public MenuItemDTO getItem() {
        return item;
    }
}
