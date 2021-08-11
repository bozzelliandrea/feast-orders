package be.feastorders.user.entity;

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
@Table(name = "APP_USER")
public class User extends BaseEntity {

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
}
