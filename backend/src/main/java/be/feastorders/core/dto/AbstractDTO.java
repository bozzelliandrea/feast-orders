package be.feastorders.core.dto;

import be.feastorders.core.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractDTO implements Serializable {

    private Long ID;
    private String name;
    private ZonedDateTime creationTimestamp;
    private String creationUser;
    private ZonedDateTime updateTimestamp;
    private String updateUser;
    private Long version;

    private AbstractDTO() {
        throw new IllegalCallerException("Cannot instance empty abstract dto");
    }

    public AbstractDTO(BaseEntity entity) {
        this.ID = entity.getID();
        this.creationTimestamp = entity.getCreationTimestamp();
        this.creationUser = entity.getCreationUser();
        this.updateTimestamp = entity.getUpdateTimestamp();
        this.updateUser = entity.getUpdateUser();
        this.version = entity.getVersion();
    }
}
