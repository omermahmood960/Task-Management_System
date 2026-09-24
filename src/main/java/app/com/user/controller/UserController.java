package app.com.user.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import app.com.user.dto.UserRequestDTO;
import app.com.user.dto.UserResponseDTO;
import app.com.user.service.Impl.UserServiceImpl;
import app.com.user.service.UserService;

import java.util.List;
import java.util.UUID;

// Endpoint mapping annotations for HTTP methods
// Annotations for extracting request inputs

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserServiceImpl userServiceImp;
    private final UserService userService;

    //Constructor
    public UserController(UserServiceImpl userServiceImp, UserService userService) {
        this.userServiceImp = userServiceImp;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponse = this.userServiceImp.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID userId) {
        UserResponseDTO userResponseDTO = userServiceImp.getUserById(userId);
        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "Authenticated as: " + authentication.getName();
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> allUsers = userServiceImp.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID userId, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userServiceImp.updateUserById(userId, userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userServiceImp.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
