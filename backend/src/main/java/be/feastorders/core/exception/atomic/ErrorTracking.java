package be.feastorders.core.exception.atomic;


import be.feastorders.core.entity.Auditable;
import lombok.*;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ERROR_TRACKING")
@NoArgsConstructor
@Getter
@Setter
public class ErrorTracking extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
