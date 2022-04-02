package controller;

import arch.security.annotation.Admin;
import arch.security.dto.UserDTO;
import arch.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
