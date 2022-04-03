package business.category.dto;

import arch.dto.AbstractDTO;
import arch.validation.Required;
import arch.validation.RequiredMethod;
import business.menuitem.dto.MenuItemDTO;
import business.printer.dto.PrinterCfgDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends AbstractDTO {

    private static final long serialVersionUID = -4011189514121354875L;

    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private String name;
    private String description;
    private String color;
    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private String processingZone;
    private List<MenuItemDTO> menuItemList = new ArrayList<>();
    private List<PrinterCfgDTO> printerCfgList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProcessingZone() {
        return processingZone;
    }

    public void setProcessingZone(String processingZone) {
        this.processingZone = processingZone;
    }

    public List<MenuItemDTO> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItemDTO> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public List<PrinterCfgDTO> getPrinterCfgList() {
        return printerCfgList;
    }

    public void setPrinterCfgList(List<PrinterCfgDTO> printerCfgList) {
        this.printerCfgList = printerCfgList;
    }
}
