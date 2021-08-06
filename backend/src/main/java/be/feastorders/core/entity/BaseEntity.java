package be.feastorders.core.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@Entity
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "CREATION-TIMESTAMP", nullable = false)
    private ZonedDateTime creationTimestamp;

    @NotNull
    @Column(name = "CREATION-USER", nullable = false)
    private String creationUser;

    @NotNull
    @Column(name = "UPDATE-TIMESTAMP", nullable = false)
    private ZonedDateTime updateTimestamp;

    @NotNull
    @Column(name = "UPDATE-USER", nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "VERSION", nullable = false)
    private Long version;
}