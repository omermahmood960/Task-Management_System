package app.com.user.controller;
import app.com.security.JWTService;
import app.com.user.dto.AuthenticationRequest;
import app.com.user.dto.AuthenticationResponse;
import app.com.user.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final JWTService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(JWTService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(
                authenticationService.authenticate(authenticationRequest)
        );
    }
}