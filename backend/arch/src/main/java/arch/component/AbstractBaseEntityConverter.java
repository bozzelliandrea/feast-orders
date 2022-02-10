package arch.component;

import arch.dto.AbstractDTO;
import arch.entity.BaseEntity;

public abstract class AbstractBaseEntityConverter<E extends BaseEntity, DTO extends AbstractDTO>
        extends AbstractConverter<E, DTO>
        implements Converter {

}
