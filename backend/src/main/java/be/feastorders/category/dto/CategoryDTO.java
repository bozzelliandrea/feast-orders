package be.feastorders.category.dto;

import be.feastorders.category.entity.Category;
import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.printer.dto.PrinterCfgDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
            this.printerCfgList =  entity.getPrinterCfgs().stream()
                    .map(PrinterCfgDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
