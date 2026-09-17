package app.com.project.service;

import app.com.entities.Project;
import app.com.project.dto.ProjectRequestDTO;
import app.com.project.dto.ProjectResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectResponseDTO> getAllProjects();
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);
    ProjectResponseDTO updateProjectById(ProjectRequestDTO projectRequestDTO, UUID projectId);
    void deleteProject(UUID projectId);
    ProjectResponseDTO getProjectById(UUID projectId);
}
