package com.example.WorkedTogetherAnalyzer.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "WorkedOn")
@IdClass(WorkedOnId.class)
public class WorkedOn implements Serializable {

    @Id
    @Column(name = "empID")
    private Long empID;

    @Id
    @Column(name = "projectID")
    private Long projectID;

    @ManyToOne
    @MapsId("empID")
    @JoinColumn(name = "empID")
    private Employee employee;

    @ManyToOne
    @MapsId("projectID")
    @JoinColumn(name = "projectID")
    private Project project;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    // Constructors, getters, and setters

    public WorkedOn() {
    }

    public WorkedOn(Long empID, Long projectID, Date startDate, Date endDate) {
        this.empID = empID;
        this.projectID = projectID;
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

    // Rest of the getters and setters
}


