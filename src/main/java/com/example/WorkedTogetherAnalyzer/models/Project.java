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

    public Project() {
        // Default constructor for JPA
    }

    public Project(Long projectID, Date dateFrom, Date dateTo) {
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}