package controller;

import arch.security.annotation.Admin;
import arch.security.dto.UserDTO;
import arch.security.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/user", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Admin
    @PutMapping("/role")
    public ResponseEntity<UserDTO> updateRole(@RequestBody UserDTO request) {
        return ResponseEntity.ok(userDetailsService.updateRole(request));
    }

    @Admin
    @DeleteMapping
    public ResponseEntity<UserDTO> deleteUser(@RequestBody UserDTO request) {
        return ResponseEntity.ok(userDetailsService.deleteUser(request));
    }
}
