package com.example.WorkedTogetherAnalyzer.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectID;
    @Column(nullable = false)
    private Date dateFrom;
    @Column
    private Date dateTo;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Project() {
        // Default constructor for JPA
    }

    public Project(Long projectID, Date dateFrom, Date dateTo, Employee employee) {
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.employee = employee;
    }
    // Constructors, getters, and setters

    public Long getId() {
        return projectID;
    }
    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}