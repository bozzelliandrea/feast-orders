package arch.security.dto;

import arch.validation.Required;
import arch.validation.RequiredMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {

    @NotBlank
    @Required(RequiredMethod.CREATE)
    private String username;

    @NotBlank
    @Required(RequiredMethod.CREATE)
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = 40)
    @Required(RequiredMethod.CREATE)
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
