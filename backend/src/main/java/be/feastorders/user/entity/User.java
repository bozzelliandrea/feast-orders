package be.feastorders.user.entity;

import be.feastorders.core.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6908471920370959695L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "APP_USER_GEN", sequenceName = "APP_USER_GEN_SQ", allocationSize = 1)
    @GeneratedValue(generator = "APP_USER_GEN", strategy = GenerationType.SEQUENCE)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DEVICE")
    private String device;

    @Override
    public Long getID() {
        return this.ID;
    }

    @Override
    public void setID(Long ID) {
        this.ID = ID;
    }
}
