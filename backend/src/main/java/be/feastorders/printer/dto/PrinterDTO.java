package be.feastorders.printer.dto;

import be.feastorders.printer.entity.Printer;
import be.feastorders.core.dto.AbstractDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PrinterDTO extends AbstractDTO {

    private String name;
    private String description;
    private String paperFormat;
    private String copiesNumber;

    public PrinterDTO(Printer entity) {
        super(entity);
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.paperFormat = entity.getPaperFormat();
        this.copiesNumber = entity.getCopiesNumber();
    }
}
