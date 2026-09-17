package app.com.task.controller;

import app.com.task.dto.TaskRequestDTO;
import app.com.task.dto.TaskResponseDTO;
import app.com.task.service.Impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = this.taskService.createTask(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> allTasks = this.taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.FOUND).body(allTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable UUID taskId) {
        TaskResponseDTO taskResponseDTO = this.taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskResponseDTO);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable UUID taskId) {
        this.taskService.deleteTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTaskById(@PathVariable UUID taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = this.taskService.updateTaskById(taskRequestDTO, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(taskResponseDTO);
    }
}