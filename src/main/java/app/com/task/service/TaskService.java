package app.com.task.service;

import app.com.task.dto.TaskRequestDTO;
import app.com.task.dto.TaskResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(UUID taskId);
    TaskResponseDTO updateTaskById(TaskRequestDTO taskRequestDTO, UUID taskId);
    void deleteTaskById(UUID taskId);
}