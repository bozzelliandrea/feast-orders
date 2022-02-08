package be.feastorders.core.component;

import be.feastorders.core.dto.AbstractDTO;
import be.feastorders.core.entity.BaseEntity;

public abstract class AbstractBaseEntityConverter<E extends BaseEntity, DTO extends AbstractDTO>
        extends AbstractConverter<E, DTO>
        implements Converter {

}
