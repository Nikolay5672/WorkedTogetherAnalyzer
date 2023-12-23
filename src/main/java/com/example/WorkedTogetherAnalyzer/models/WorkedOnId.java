package com.example.WorkedTogetherAnalyzer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Objects;

public class WorkedOnId implements Serializable {
    @NotBlank(message = "Project ID is required")
    @Positive(message = "Project ID must be a positive value")
    private Long projectId;
    @NotBlank(message = "Employee ID is required")
    @Positive(message = "Employee ID must be a positive value")
    private Long empId;

    // Constructors, getters, and setters

    public WorkedOnId() {
    }

    public WorkedOnId(Long projectId, Long empId) {
        this.projectId = projectId;
        this.empId = empId;
    }

    // Implement equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkedOnId that = (WorkedOnId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, empId);
    }

    // Getters and setters

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }
}


