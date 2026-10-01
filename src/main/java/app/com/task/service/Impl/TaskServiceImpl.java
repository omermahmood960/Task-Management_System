package app.com.task.service.Impl;

import app.com.entities.Project;
import app.com.entities.Task;
import app.com.entities.User;
import app.com.exceptions.ResourceNotFoundException;
import app.com.project.repository.ProjectRepository;
import app.com.task.dto.TaskRequestDTO;
import app.com.task.dto.TaskResponseDTO;
import app.com.task.mapper.TaskMapper;
import app.com.task.repository.TaskRepository;
import app.com.task.service.TaskService;
import app.com.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository, TaskMapper taskMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("User Not found"));
    }
    private void checkTaskOwnership(User loggedInUser, Task task) throws AccessDeniedException {
        if (task.getAssignedUser() == null) {
            throw new AccessDeniedException("Task is not assigned to any user");
        }
        if (!task.getAssignedUser().getId().equals(loggedInUser.getId())) {
            throw new AccessDeniedException("You are not authorized to access this task");
        }
    }
    @Override
    public TaskResponseDTO updateTaskById(TaskRequestDTO taskRequestDTO, UUID taskId) {
        Project existedProject = null;
        Task existingTask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("The task doesn't exist with that id"));
        if (taskRequestDTO.getProjectId() != null) {
            existedProject = this.projectRepository.findById(taskRequestDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("Can't update the task"));
        }
        User loggedInUser = getLoggedInUser();
        try {
            checkTaskOwnership(loggedInUser, existingTask);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        Task taskToUpdate = this.taskMapper.mapToEntityForUpdate(taskRequestDTO, existingTask, existedProject);
        Task updatedTask = this.taskRepository.save(taskToUpdate);
        return taskMapper.mapToDto(updatedTask);
    }

    @Transactional
    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Project project = this.projectRepository.findById(taskRequestDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("Can't create a task as the project doesn't exist "));
        Task taskToCreate = this.taskMapper.mapToEntity(taskRequestDTO, project);
        Task createdTask = this.taskRepository.save(taskToCreate);
        return taskMapper.mapToDto(createdTask);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> allTasks = this.taskRepository.findAll();
        return allTasks.stream().map(task -> this.taskMapper.mapToDto(task)).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public TaskResponseDTO getTaskById(UUID taskId) {
        Task ExistedTask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task doesn't exist with that Id"));
        return this.taskMapper.mapToDto(ExistedTask);
    }
    @Transactional
    @Override
    public void deleteTaskById(UUID taskId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task doesn't exist"));
        User loggedInUser = getLoggedInUser();
        try {
            checkTaskOwnership(loggedInUser, task);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        this.taskRepository.deleteById(taskId);

    }
}