package arch.security.dto;

import arch.entity.ERole;
import arch.entity.Role;
import arch.entity.User;
import arch.validation.Required;
import arch.validation.RequiredMethod;

import java.io.Serializable;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7850942551993984457L;

    @Required({RequiredMethod.UPDATE})
    private String role;
    @Required({RequiredMethod.UPDATE, RequiredMethod.DELETE})
    private String username;
    private Boolean active;

    public UserDTO() {
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.role = user.getRoles().stream()
                .map(Role::getName)
                .map(ERole::toString)
                .collect(Collectors.joining(", "));
        this.active = user.isActive();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
