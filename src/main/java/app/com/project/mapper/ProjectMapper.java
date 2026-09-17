package app.com.project.mapper;

import app.com.entities.Project;
import app.com.enums.ProjectStatus;
import app.com.project.dto.ProjectRequestDTO;
import app.com.project.dto.ProjectResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project mapToEntity(ProjectRequestDTO projectRequestDTO) {
        Project project = new Project();
        project.setName(projectRequestDTO.getName());
        project.setDescription(projectRequestDTO.getDescription());
        project.setStatus(projectRequestDTO.getStatus());
        return project;
    }

    public Project mapToEntityForUpdate(ProjectRequestDTO projectRequestDTO, Project project) {
        if (projectRequestDTO.getName() != null) {
            project.setName(projectRequestDTO.getName());
        }
        if (projectRequestDTO.getDescription() != null) {
            project.setDescription(projectRequestDTO.getDescription());
        }
        if (projectRequestDTO.getStatus() != null) {
            project.setStatus(projectRequestDTO.getStatus());
        }
        return project;
    }

    public ProjectResponseDTO mapToDto(Project project) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setId(project.getId());
        projectResponseDTO.setName(project.getName());
        projectResponseDTO.setDescription(project.getDescription());
        projectResponseDTO.setStatus(project.getStatus().toString());
        projectResponseDTO.setCreatedAt(project.getCreatedAt());
        projectResponseDTO.setUpdatedAt(project.getUpdatedAt());
        return projectResponseDTO;
    }
}