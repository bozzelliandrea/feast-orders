package be.feastorders.printer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRINTERATTR")
public class PrinterAttribute implements Serializable {

    private static final long serialVersionUID = -215132403521063058L;

    @Id
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "TYPE", nullable = false)
    private String type;

    @OneToMany(mappedBy = "attr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PrinterCfgAttribute> cfgAttrs = new ArrayList<>();

}

