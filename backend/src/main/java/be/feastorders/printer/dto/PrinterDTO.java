package be.feastorders.printer.dto;

import be.feastorders.printer.entity.Printer;
import be.feastorders.core.dto.AbstractDTO;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrinterDTO extends AbstractDTO {

    private static final long serialVersionUID = 3209792370649310652L;

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
