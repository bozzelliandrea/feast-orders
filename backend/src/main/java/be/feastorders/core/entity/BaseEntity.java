package be.feastorders.core.entity;

import be.feastorders.security.service.UserDetailsImpl;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends Auditable<String> implements BaseEntityID<Long> {

    private static final long serialVersionUID = -3373920749628688771L;

    @NotNull
    @Column(name = "VERSION", nullable = false)
    @Version
    private Long version;

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

    @Override
    public Long getID() {
        return null;
    }

    @Override
    public void setID(Long ID) {
        System.out.println("Bean Override");
    }
}