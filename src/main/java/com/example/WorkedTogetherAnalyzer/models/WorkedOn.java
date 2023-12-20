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
    private int empID;

    @Id
    @Column(name = "projectID")
    private int projectID;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    // Constructors, getters, and setters

    public WorkedOn() {
    }

    public WorkedOn(int empID, int projectID, Date startDate, Date endDate) {
        this.empID = empID;
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
}

