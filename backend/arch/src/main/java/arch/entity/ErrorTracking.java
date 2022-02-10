package arch.entity;


import javax.persistence.*;


@Entity
@Table(name = "ERROR_TRACKING")
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

    public ErrorTracking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
