package com.example.WorkedTogetherAnalyzer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.Date;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empID")
    private Long empID;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;

    // Constructors

    public Employee() {
        // Default constructor for JPA
        dateOfBirth = null;
    }

    public Employee(Long empID, String firstName, String lastName, String email, Date dateOfBirth) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and setters

    public Long getId() {
        return empID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}


