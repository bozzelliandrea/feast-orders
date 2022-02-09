package component;

import dto.AbstractDTO;
import entity.BaseEntity;

public interface Converter {

    default void convertDTO2BaseEntity(AbstractDTO dto, BaseEntity entity) {

        entity.setCreationTimestamp(dto.getCreationTimestamp());
        entity.setCreationUser(dto.getCreationUser());
        entity.setUpdateTimestamp(dto.getUpdateTimestamp());
        entity.setUpdateUser(dto.getUpdateUser());
        entity.setVersion(dto.getVersion());
    }
}
