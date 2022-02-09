package be.feastorders.category.dto;

import be.feastorders.category.entity.Category;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.printer.dto.PrinterCfgDTO;
import dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO extends AbstractDTO {

    private static final long serialVersionUID = -4011189514121354875L;

    private String name;
    private String description;
    private String color;
    private String processingZone;
    private List<MenuItemDTO> menuItemList = new ArrayList<>();
    private List<PrinterCfgDTO> printerCfgList = new ArrayList<>();


    public CategoryDTO(Category entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.color = entity.getColor();

        if (entity.getPrinterCfgs() != null && !entity.getPrinterCfgs().isEmpty()) {
            this.printerCfgList = entity.getPrinterCfgs().stream()
                    .map(PrinterCfgDTO::new)
                    .collect(Collectors.toList());
        }
    }

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
