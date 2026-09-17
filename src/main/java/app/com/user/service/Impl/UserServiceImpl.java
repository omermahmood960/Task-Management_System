package app.com.user.service.Impl;

import app.com.entities.Task;
import app.com.exceptions.ResourceNotFoundException;
import app.com.task.repository.TaskRepository;
import app.com.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.com.user.dto.UserRequestDTO;
import app.com.user.dto.UserResponseDTO;
import app.com.entities.User;
import app.com.user.mapper.UserMapper;
import app.com.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;

    //Constructor
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Task ExistedTask = this.taskRepository.findById(userRequestDTO.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("Can't create a user as the requested Task doesn't exist"));
        User user = this.userMapper.mapToEntity(userRequestDTO, ExistedTask);
        User createdUser = this.userRepository.save(user);
        return this.userMapper.mapToDTO(createdUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO getUserById(UUID userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with this id is not present"));
        return this.userMapper.mapToDTO(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this.userMapper::mapToDTO).toList();
    }
    @Transactional
    @Override
    public UserResponseDTO updateUserById(UUID userId, UserRequestDTO userRequestDTO) {
        Task existedTask = null;
        if (userRequestDTO.getTaskId() != null) {
            existedTask = this.taskRepository.findById(userRequestDTO.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("Can't update the user as Task Id doesn't exist"));
        }
        User ExistedUser = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with that idea is not present in the database"));
        User userToUpdate = this.userMapper.mapToEntityForUpdate(userRequestDTO, ExistedUser, existedTask);
        User updatedUser = this.userRepository.save(userToUpdate);
        return this.userMapper.mapToDTO(updatedUser);
    }

    @Override
    public void deleteUserById(UUID userId) {
        User existedUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        this.userRepository.deleteById(userId);
    }

}
