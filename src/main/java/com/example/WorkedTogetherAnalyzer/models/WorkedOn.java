package com.example.WorkedTogetherAnalyzer.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "WorkedOn")
@IdClass(WorkedOnId.class)
public class WorkedOn implements Serializable {

    @Id
    @Column(name = "empId")  // Match the name in WorkedOnId
    private Long empId;

    @Id
    @Column(name = "projectId")  // Match the name in WorkedOnId
    private Long projectId;

    @ManyToOne
    @MapsId("empId")
    @JoinColumn(name = "empId")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "projectId")
    private Project project;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    // Constructors, getters, and setters

    public WorkedOn() {
    }

    public WorkedOn(Long empID, Long projectID, Date startDate, Date endDate) {
        this.empId = empID;
        this.projectId = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters

    // Add getters and setters for Employee and Project

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    // Rest of the getters and setters
}


