package be.feastorders.core.exception.atomic;


import be.feastorders.core.entity.Auditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ERROR_TRACKING")
@NoArgsConstructor
@Getter
@Setter
public class ErrorTracking extends Auditable<String> {

    @Id
    @SequenceGenerator(name = "ERR_TRACK_GEN", sequenceName = "ERR_TRACK_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "ERR_TRACK_GEN", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TYPE")
    private String type;

    public ErrorTracking(String message, String type) {
        this.message = message;
        this.type = type;
    }
}
