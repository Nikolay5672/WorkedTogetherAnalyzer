package com.example.WorkedTogetherAnalyzer.controllers;

import com.example.WorkedTogetherAnalyzer.models.Project;
import com.example.WorkedTogetherAnalyzer.services.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getAll")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Object> getProjectById(@PathVariable @Positive(message = "Project ID must be a positive value") Long projectId) {
        Optional<Project> project = projectService.getProjectById(projectId);

        return project.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found"));
    }


    @PostMapping("/add")
    public ResponseEntity<?> createProject(@RequestBody @Valid Project project) {
        try {
            Project savedProject = projectService.saveProject(project);
            return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(
            @PathVariable @Positive(message = "Project ID must be a positive value") Long projectId,
            @RequestBody @Valid Project updatedProject) {
        try {
            Optional<Project> existingProject = projectService.getProjectById(projectId);
            if (existingProject.isPresent()) {
                updatedProject.setProjectID(projectId);
                Project savedProject = projectService.saveProject(updatedProject);
                return new ResponseEntity<>(savedProject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable @Positive(message = "Project ID must be a positive value") Long projectId) {
        try {
            Optional<Project> existingProject = projectService.getProjectById(projectId);
            if (existingProject.isPresent()) {
                projectService.deleteProject(projectId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

