package be.feastorders.printer.entity;

import be.feastorders.core.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "PRINTER")
public class Printer extends BaseEntity {

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PAPER_FORMAT", nullable = false)
    private String paperFormat;

    @NotNull
    @Column(name = "COPIES_NUMBER", nullable = false)
    private String copiesNumber;
}
