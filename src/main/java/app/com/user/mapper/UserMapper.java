package app.com.user.mapper;

import app.com.entities.Task;
import app.com.user.dto.UserRequestDTO;
import app.com.user.dto.UserResponseDTO;
import app.com.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapToEntity(UserRequestDTO userRequestDTO, Task task) {
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setName(userRequestDTO.getName());
        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);
        user.setTask(task);
        user.setRoles(userRequestDTO.getRoles());
        return user;
    }

    public User mapToEntityForUpdate(UserRequestDTO userRequestDTO, User user, Task task) {
        if (userRequestDTO.getEmail() != null) {
            user.setEmail(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getName() != null) {
            user.setName(userRequestDTO.getName());
        }
        if (userRequestDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }
        if (userRequestDTO.getTaskId() != null) {
            user.setTask(task);
        }
        if (userRequestDTO.getRoles() != null) {
            user.setRoles(userRequestDTO.getRoles());
        }

        return user;
    }

    public UserResponseDTO mapToDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPassword(user.getPassword());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt());
        userResponseDTO.setId(user.getTask().getId());
        userResponseDTO.setRoles(user.getRoles());
        return userResponseDTO;
    }
}
