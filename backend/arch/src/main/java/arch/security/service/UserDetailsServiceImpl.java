package arch.security.service;

import arch.entity.ERole;
import arch.entity.Role;
import arch.entity.User;
import arch.exception.errors.RoleNotFoundException;
import arch.repository.RoleRepository;
import arch.repository.UserRepository;
import arch.security.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public UserDTO updateRole(UserDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + request.getUsername()));

        Role userRole;
        try {
            ERole eRole = ERole.valueOf(request.getRole());
            userRole = roleRepository.findByName(eRole)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
        } catch (Exception e) {
            throw new RoleNotFoundException(e);
        }

        user.getRoles().add(userRole);
        userRepository.saveAndFlush(user);

        return new UserDTO(request.getUsername());
    }

    public UserDTO deleteUser(UserDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + request.getUsername()));

        userRepository.delete(user);

        return new UserDTO();
    }
}
