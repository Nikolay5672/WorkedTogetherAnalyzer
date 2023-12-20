package com.example.WorkedTogetherAnalyzer.repositories;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedOnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedOnRepository extends JpaRepository<WorkedOn, WorkedOnId> {
    // Additional custom queries can be added here if needed
}
