package arch.entity;

import arch.security.service.UserDetailsImpl;
import com.sun.istack.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = -3373920749628688771L;

    @NotNull
    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;

    abstract public Long getID();

    abstract public void setID(Long ID);

    @PrePersist
    public void prePersist() {
        String createdByUser = getUsernameOfAuthenticatedUser();
        this.setCreationUser(createdByUser);
        this.setUpdateUser(createdByUser);
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdateUser(getUsernameOfAuthenticatedUser());
    }

    private String getUsernameOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        return ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}