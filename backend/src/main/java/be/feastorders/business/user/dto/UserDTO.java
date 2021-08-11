package be.feastorders.business.user.dto;

import be.feastorders.business.user.entity.User;
import be.feastorders.business.core.dto.AbstractDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AbstractDTO {

    private String name;
    private String surname;
    private String password;
    private String device;

    public UserDTO(User entity) {
        super(entity);
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.password = entity.getPassword();
        this.device = entity.getDevice();
    }
}
