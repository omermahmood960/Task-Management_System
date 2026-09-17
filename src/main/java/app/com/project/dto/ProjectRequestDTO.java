package app.com.project.dto;

import app.com.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectRequestDTO {
    @NotBlank(message = "Project name is required")
    @Size(min = 5, max = 50)
    private String name;
    @NotBlank(message = "Project Description is essential")
    private String description;
    @NotNull(message = "Project status is mandatory")
    private ProjectStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}