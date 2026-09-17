package app.com.task.mapper;

import app.com.entities.Project;
import app.com.entities.Task;
import app.com.enums.TaskPriority;
import app.com.enums.TaskStatus;
import app.com.task.dto.TaskRequestDTO;
import app.com.task.dto.TaskResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {

    public Task mapToEntity(TaskRequestDTO taskRequestDTO, Project project) {
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setPriority(taskRequestDTO.getPriority());
        task.setProject(project);
        task.setDueDate(taskRequestDTO.getDueDate());
        return task;
    }

    public Task mapToEntityForUpdate(TaskRequestDTO taskRequestDTO, Task task, Project project) {
        if (taskRequestDTO.getTitle() != null) {
            task.setTitle(taskRequestDTO.getTitle());
        }
        if (taskRequestDTO.getProjectId() != null) {
            task.setProject(project);
        }
        if (taskRequestDTO.getDueDate() != null) {
            task.setDueDate(taskRequestDTO.getDueDate());
        }
        if (taskRequestDTO.getDescription() != null) {
            task.setDescription(taskRequestDTO.getDescription());
        }
        if (taskRequestDTO.getPriority() != null) {
            task.setPriority(taskRequestDTO.getPriority());
        }
        if (taskRequestDTO.getStatus() != null) {
            task.setStatus(taskRequestDTO.getStatus());
        }
        return task;
    }

    public TaskResponseDTO mapToDto(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setUpdatedAt(task.getUpdatedAt());
        taskResponseDTO.setCreatedAt(task.getCreatedAt());
        taskResponseDTO.setProjectId(task.getProject().getId());
        taskResponseDTO.setDueDate(task.getDueDate());

        return taskResponseDTO;
    }
}