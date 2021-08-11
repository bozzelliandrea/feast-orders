package be.feastorders.business.printer.dto;

import be.feastorders.business.printer.entity.Printer;
import be.feastorders.business.core.dto.AbstractDTO;
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
