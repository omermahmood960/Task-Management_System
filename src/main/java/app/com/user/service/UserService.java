package app.com.user.service;

import java.util.List;
import java.util.UUID;

import app.com.user.dto.UserRequestDTO;
import app.com.user.dto.UserResponseDTO;

public interface UserService {

    app.com.user.dto.UserResponseDTO createUser(app.com.user.dto.UserRequestDTO request);

    app.com.user.dto.UserResponseDTO getUserById(UUID id);

    List<app.com.user.dto.UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserById(UUID id, UserRequestDTO userRequestDTO);

    void deleteUserById(UUID id);
}
