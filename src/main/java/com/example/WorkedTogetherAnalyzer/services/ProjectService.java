package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.models.Project;
import com.example.WorkedTogetherAnalyzer.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project with id " + id + " not found");
        }
        return projectRepository.findById(id);
    }

    public Project saveProject(@Valid Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project with id " + id + " not found");
        }
        projectRepository.deleteById(id);
    }

    // Additional custom methods if needed
}

