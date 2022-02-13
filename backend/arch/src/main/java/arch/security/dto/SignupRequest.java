package arch.security.dto;

import arch.validation.Required;
import arch.validation.RequiredMethod;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @Required(RequiredMethod.CREATE)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Required(RequiredMethod.CREATE)
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    @Required(RequiredMethod.CREATE)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
