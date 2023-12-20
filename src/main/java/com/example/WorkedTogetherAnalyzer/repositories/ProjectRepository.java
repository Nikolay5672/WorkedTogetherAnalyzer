package com.example.WorkedTogetherAnalyzer.repositories;

import com.example.WorkedTogetherAnalyzer.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Additional custom queries can be added here if needed
}
