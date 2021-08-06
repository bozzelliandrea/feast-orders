package be.feastorders.core.dto;

import be.feastorders.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractDTO {

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
        this.name = entity.getName();
        this.creationTimestamp = entity.getCreationTimestamp();
        this.creationUser = entity.getCreationUser();
        this.updateTimestamp = entity.getUpdateTimestamp();
        this.updateUser = entity.getUpdateUser();
        this.version = entity.getVersion();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        AbstractDTO dto = (AbstractDTO) obj;

        return Objects.equals(dto.getID(), this.ID)
                && Objects.equals(dto.getName(), this.name)
                && Objects.equals(dto.getCreationTimestamp(), this.creationTimestamp)
                && Objects.equals(dto.getCreationUser(), this.creationUser)
                && Objects.equals(dto.getUpdateTimestamp(), this.updateTimestamp)
                && Objects.equals(dto.getUpdateUser(), this.updateUser)
                && Objects.equals(dto.getVersion(), this.version);
    }
}
