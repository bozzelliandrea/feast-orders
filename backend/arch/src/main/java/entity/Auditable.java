package entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

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

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public U getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(U creationUser) {
        this.creationUser = creationUser;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public U getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(U updateUser) {
        this.updateUser = updateUser;
    }
}

