package controller;

import arch.exception.FeastErrorResponse;
import arch.security.annotation.Admin;
import arch.security.dto.JwtResponse;
import arch.security.dto.LoginRequest;
import arch.security.dto.ResetPasswordRequest;
import arch.security.dto.SignupRequest;
import arch.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("Login with an existing and valid User.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "user found and load in security session", response = JwtResponse.class),
            @ApiResponse(code = 403, message = "the entered password is incorrect", response = FeastErrorResponse.class),
            @ApiResponse(code = 404, message = "user not valid or not exist", response = FeastErrorResponse.class)
    })
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @ApiOperation(value = "Register new user (need Admin privilege).", authorizations = @Authorization("ADMIN"))
    @ApiResponses({
            @ApiResponse(code = 200, message = "new user has been created", response = String.class),
            @ApiResponse(code = 409, message = "the entered username is already taken", response = FeastErrorResponse.class),
            @ApiResponse(code = 409, message = "the entered email is already in use", response = FeastErrorResponse.class),
    })
    @PostMapping("/signup")
    @Admin
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authService.register(signUpRequest));
    }

    @ApiOperation("Reset existing user password.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "user password has been update correctly", response = String.class),
            @ApiResponse(code = 403, message = "the entered password is incorrect", response = FeastErrorResponse.class),
            @ApiResponse(code = 404, message = "user not valid or not exist", response = FeastErrorResponse.class)
    })
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authService.reset(resetPasswordRequest));
    }
}
