package arch.dto;

import arch.entity.BaseEntity;
import arch.validation.Required;
import arch.validation.RequiredMethod;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1666141927066453744L;

    @Required({RequiredMethod.READ, RequiredMethod.DELETE, RequiredMethod.UPDATE})
    private Long id;

    private Date creationTimestamp;
    private String creationUser;

    private Date updateTimestamp;
    private String updateUser;

    @Required({RequiredMethod.UPDATE})
    private Long version;

    public AbstractDTO(AbstractDTO dto) {
        this.id = dto.getId();
        this.creationTimestamp = dto.getCreationTimestamp();
        this.creationUser = dto.getCreationUser();
        this.updateTimestamp = dto.getUpdateTimestamp();
        this.updateUser = dto.getUpdateUser();
        this.version = dto.getVersion();
    }

    public AbstractDTO(BaseEntity entity) {
        this.id = entity.getID();
        this.creationTimestamp = entity.getCreationTimestamp();
        this.creationUser = entity.getCreationUser();
        this.updateTimestamp = entity.getUpdateTimestamp();
        this.updateUser = entity.getUpdateUser();
        this.version = entity.getVersion();
    }

    public AbstractDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
