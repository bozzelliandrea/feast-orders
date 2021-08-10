package be.feastorders.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends Auditable<String> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NonNull
    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;
}