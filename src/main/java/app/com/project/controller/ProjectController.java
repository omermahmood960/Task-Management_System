package app.com.project.controller;

import app.com.project.dto.ProjectRequestDTO;
import app.com.project.dto.ProjectResponseDTO;
import app.com.project.service.Impl.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO) {
        ProjectResponseDTO projectResponseDTO = this.projectService.createProject(projectRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponseDTO);
    }
    @GetMapping
//    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'CustomerUser')")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> getAllProjects = this.projectService.getAllProjects();
        return ResponseEntity.ok(getAllProjects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable UUID projectId) {
        ProjectResponseDTO projectResponseDTO = this.projectService.getProjectById(projectId);
        return ResponseEntity.ok(projectResponseDTO);
    }

    @DeleteMapping("/{projectId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProjectById(@PathVariable UUID projectId) {
        this.projectService.deleteProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Project deleted Successfully");
    }
    @PutMapping("/{projectId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> updateProjectById(@RequestBody @Valid ProjectRequestDTO projectRequestDTO, @PathVariable UUID projectId) {
        ProjectResponseDTO projectResponseDTO = this.projectService.updateProjectById(projectRequestDTO, projectId);
        return ResponseEntity.ok(projectResponseDTO);
    }
}