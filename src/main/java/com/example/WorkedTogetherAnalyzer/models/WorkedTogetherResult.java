package com.example.WorkedTogetherAnalyzer.models;

public class WorkedTogetherResult {

    private Long projectID;
    private Long employee1;
    private Long employee2;
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

