package be.feastorders.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    @Column(name = "CREATION_TIMESTAMP")
    @CreatedDate
    private Date creationTimestamp;

    @Column(name = "CREATION_USER")
    @CreatedBy
    private U creationUser;

    @Column(name = "UPDATE_TIMESTAMP")
    @LastModifiedDate
    private Date updateTimestamp;

    @Column(name = "UPDATE_USER")
    @LastModifiedBy
    private U updateUser;

}

