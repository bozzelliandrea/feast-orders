package controller;

import arch.dto.AbstractPagination;
import arch.security.annotation.Admin;
import arch.security.dto.UserDTO;
import arch.security.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static arch.component.PaginationUtils.DEFAULT_PAGE_SIZE;
import static arch.component.PaginationUtils.DEFAULT_PAGE_START;
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

    @Admin
    @GetMapping
    public ResponseEntity<AbstractPagination<UserDTO>> getAllUsers(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_START, required = false) int page,
                                                                   @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE, required = false) int size){
        return ResponseEntity.ok(userDetailsService.findAllWithPagination(page, size));
    }
}
