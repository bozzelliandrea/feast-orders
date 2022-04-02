package arch.security.dto;

import arch.validation.Required;
import arch.validation.RequiredMethod;

import java.io.Serializable;

public class UserDTO implements Serializable {

    @Required({RequiredMethod.UPDATE})
    private String role;
    @Required({RequiredMethod.UPDATE, RequiredMethod.DELETE})
    private String username;

    public UserDTO() {
    }

    public UserDTO(String username) {
        this.username = username;
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
}
