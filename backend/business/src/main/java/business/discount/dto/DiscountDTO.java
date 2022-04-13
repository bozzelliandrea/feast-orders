package business.discount.dto;

import arch.dto.AbstractDTO;
import arch.validation.Required;
import arch.validation.RequiredMethod;

public class DiscountDTO extends AbstractDTO {

    private static final long serialVersionUID = 6580290710448822761L;

    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private String name;
    private String description;
    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private String type;
    @Required({RequiredMethod.CREATE, RequiredMethod.UPDATE})
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
