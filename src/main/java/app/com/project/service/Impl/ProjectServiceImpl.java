package app.com.project.service.Impl;

import app.com.exceptions.ResourceNotFoundException;
import app.com.project.dto.ProjectRequestDTO;
import app.com.project.dto.ProjectResponseDTO;
import app.com.project.mapper.ProjectMapper;
import app.com.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.com.entities.Project;
import app.com.project.repository.ProjectRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }
    @Transactional
    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Project project = this.projectMapper.mapToEntity(projectRequestDTO);
        Project createdProject =  this.projectRepository.save(project);
        return this.projectMapper.mapToDto(createdProject);
    }
    @Transactional
    @Override
    public ProjectResponseDTO updateProjectById(ProjectRequestDTO projectRequestDTO, UUID projectId) {
        Project ExistedProject = this.projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project doesn't exist with this id"));
        Project projectToUpdate = this.projectMapper.mapToEntityForUpdate(projectRequestDTO, ExistedProject);
        Project updatedProject = this.projectRepository.save(projectToUpdate);
        return this.projectMapper.mapToDto(updatedProject);
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProjectResponseDTO> getAllProjects() {
        List<Project> allProjects = this.projectRepository.findAll();
        return allProjects.stream().map((this.projectMapper::mapToDto)).toList();
    }
    @Transactional
    @Override
    public ProjectResponseDTO getProjectById(UUID projectId) {
        Project project = this.projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project Doesn't exist"));
        return this.projectMapper.mapToDto(project);
    }
    @Transactional
    @Override
    public void deleteProject(UUID projectId) {
        if (!this.projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found");
        }
        projectRepository.deleteById(projectId);
    }

}
