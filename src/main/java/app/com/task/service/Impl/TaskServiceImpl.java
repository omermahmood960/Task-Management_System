package app.com.task.service.Impl;

import app.com.entities.Project;
import app.com.entities.Task;
import app.com.exceptions.ResourceNotFoundException;
import app.com.project.repository.ProjectRepository;
import app.com.task.dto.TaskRequestDTO;
import app.com.task.dto.TaskResponseDTO;
import app.com.task.mapper.TaskMapper;
import app.com.task.repository.TaskRepository;
import app.com.task.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public TaskResponseDTO updateTaskById(TaskRequestDTO taskRequestDTO, UUID taskId) {
        Project existedProject = null;
        Task existingTask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("The task doesn't exist with that id"));
        if (taskRequestDTO.getProjectId() != null) {
            existedProject = this.projectRepository.findById(taskRequestDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("Can't update the task"));
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
        if (this.taskRepository.existsById(taskId)) {
            this.taskRepository.deleteById(taskId);
        } else  {
            throw new ResourceNotFoundException("That task doesn't exist");
        }
    }
}