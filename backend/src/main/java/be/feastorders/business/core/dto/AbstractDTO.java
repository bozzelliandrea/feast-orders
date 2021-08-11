package be.feastorders.business.core.dto;

import be.feastorders.business.core.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1666141927066453744L;

    private Long ID;
    private Date creationTimestamp;
    private String creationUser;
    private Date updateTimestamp;
    private String updateUser;
    private Long version;

    public AbstractDTO(BaseEntity entity) {
        this.ID = entity.getID();
        this.creationTimestamp = entity.getCreationTimestamp();
        this.creationUser = entity.getCreationUser();
        this.updateTimestamp = entity.getUpdateTimestamp();
        this.updateUser = entity.getUpdateUser();
        this.version = entity.getVersion();
    }
}
