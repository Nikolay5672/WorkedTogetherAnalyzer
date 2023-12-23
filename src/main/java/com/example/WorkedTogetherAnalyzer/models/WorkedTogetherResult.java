package com.example.WorkedTogetherAnalyzer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class WorkedTogetherResult {

    @NotBlank(message = "Project ID is required")
    @Positive(message = "Project ID must be a positive value")
    private Long projectID;

    @NotBlank(message = "Employee 1 ID is required")
    @Positive(message = "Employee 1 ID must be a positive value")
    private Long employee1;

    @NotBlank(message = "Employee 2 ID is required")
    @Positive(message = "Employee 2 ID must be a positive value")
    private Long employee2;

    @NotBlank(message = "Total days worked is required")
    @Positive(message = "Total days worked must be a positive value")
    private Long totalDaysWorked;

    // Constructors, getters, and setters

    public WorkedTogetherResult() {
    }

    public WorkedTogetherResult(Long projectID, Long employee1, Long employee2, Long totalDaysWorked) {
        this.projectID = projectID;
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.totalDaysWorked = totalDaysWorked;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public Long getEmployee1() {
        return employee1;
    }

    public void setEmployee1(Long employee1) {
        this.employee1 = employee1;
    }

    public Long getEmployee2() {
        return employee2;
    }

    public void setEmployee2(Long employee2) {
        this.employee2 = employee2;
    }

    public Long getTotalDaysWorked() {
        return totalDaysWorked;
    }

    public void setTotalDaysWorked(Long totalDaysWorked) {
        this.totalDaysWorked = totalDaysWorked;
    }
}

