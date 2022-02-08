package component;

import dto.AbstractDTO;
import entity.BaseEntity;

public abstract class AbstractBaseEntityConverter<E extends BaseEntity, DTO extends AbstractDTO>
        extends AbstractConverter<E, DTO>
        implements Converter {

}
