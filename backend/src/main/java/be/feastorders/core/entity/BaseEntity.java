package be.feastorders.core.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends Auditable<String> implements BaseEntityID<Long>{

    private static final long serialVersionUID = -3373920749628688771L;

    @NotNull
    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;

    @Override
    public Long getID() {
        return null;
    }

    @Override
    public void setID(Long ID) {
        System.out.println("Bean Override");
    }
}