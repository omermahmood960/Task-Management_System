package app.com.task.dto;

import app.com.enums.TaskPriority;
import app.com.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class TaskRequestDTO {
    @NotBlank(message = "Task title shouldn't be empty")
    private String title;
    @NotBlank(message = "Task description shouldn't be empty")
    private String description;
    @NotNull(message = "Please mention the task status")
    private TaskStatus status;
    @NotNull(message = "Please mention the task priority")
    private TaskPriority priority;
    @NotBlank(message = "Please mention the task's due date")
    private LocalDate dueDate;
    private UUID projectId;

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}