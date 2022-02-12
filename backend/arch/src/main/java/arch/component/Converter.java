package arch.component;

import arch.dto.AbstractDTO;
import arch.entity.BaseEntity;

public interface Converter {

    default void convertDTO2BaseEntity(AbstractDTO dto, BaseEntity entity) {
        entity.setCreationTimestamp(dto.getCreationTimestamp());
        entity.setCreationUser(dto.getCreationUser());
        entity.setUpdateTimestamp(dto.getUpdateTimestamp());
        entity.setUpdateUser(dto.getUpdateUser());
        entity.setVersion(dto.getVersion());
    }

    default void convertEntity2DTO(BaseEntity entity, AbstractDTO dto) {
        dto.setCreationTimestamp(entity.getCreationTimestamp());
        dto.setCreationUser(entity.getCreationUser());
        dto.setUpdateTimestamp(entity.getUpdateTimestamp());
        dto.setUpdateUser(entity.getUpdateUser());
        dto.setVersion(entity.getVersion());
    }
}
