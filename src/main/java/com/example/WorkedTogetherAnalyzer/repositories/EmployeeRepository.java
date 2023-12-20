package com.example.WorkedTogetherAnalyzer.repositories;

import com.example.WorkedTogetherAnalyzer.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Additional custom queries can be added here if needed
}

