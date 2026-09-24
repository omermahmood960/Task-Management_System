package app.com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @GetMapping
    public ResponseEntity<String> testAuthentication() {
        return ResponseEntity.ok("Auhtentication Successfull");
    }
}