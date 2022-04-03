package controller;

import arch.security.annotation.Admin;
import arch.security.dto.JwtResponse;
import arch.security.dto.LoginRequest;
import arch.security.dto.ResetPasswordRequest;
import arch.security.dto.SignupRequest;
import arch.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Admin
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authService.register(signUpRequest));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authService.reset(resetPasswordRequest));
    }
}
