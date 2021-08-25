package be.feastorders.printer.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REPORTTEMPLATE")
public class ReportTemplate implements Serializable {

    private static final long serialVersionUID = -6380460841016754593L;

    @Id
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "FILEPATH", nullable = false)
    private String filepath;

}
