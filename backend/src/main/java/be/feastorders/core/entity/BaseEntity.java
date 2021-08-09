package be.feastorders.core.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull
    @Column(name = "CREATION_TIMESTAMP", nullable = false)
    @CreatedDate
    private ZonedDateTime creationTimestamp;

    @NotNull
    @Column(name = "CREATION_USER", nullable = false)
    @CreatedBy
    private String creationUser;

    @NotNull
    @Column(name = "UPDATE_TIMESTAMP", nullable = false)
    @LastModifiedDate
    private ZonedDateTime updateTimestamp;

    @NotNull
    @Column(name = "UPDATE_USER", nullable = false)
    @LastModifiedBy
    private String updateUser;

    @NotNull
    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;
}