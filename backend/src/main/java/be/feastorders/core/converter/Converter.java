package be.feastorders.core.converter;

import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.core.entity.BaseEntity;

import java.util.Objects;

public interface Converter {

    default void convertDTO2BaseEntity(AbstractDTO dto, BaseEntity entity) {

        if (Objects.nonNull(dto.getID()))
            entity.setID(dto.getID());

        entity.setCreationTimestamp(dto.getCreationTimestamp());
        entity.setCreationUser(dto.getCreationUser());
        entity.setUpdateTimestamp(dto.getUpdateTimestamp());
        entity.setUpdateUser(dto.getUpdateUser());
        entity.setVersion(dto.getVersion());
    }
}
