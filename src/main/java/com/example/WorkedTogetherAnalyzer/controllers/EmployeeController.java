package com.example.WorkedTogetherAnalyzer.controllers;

import com.example.WorkedTogetherAnalyzer.models.Employee;
import com.example.WorkedTogetherAnalyzer.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable @Positive(message = "Employee ID must be a positive value") Long employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable @Positive(message = "Employee ID must be a positive value") Long employeeId,
            @RequestBody @Valid Employee updatedEmployee) {
        try {
            Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);
            if (existingEmployee.isPresent()) {
                updatedEmployee.setEmpID(employeeId);
                Employee savedEmployee = employeeService.saveEmployee(updatedEmployee);
                return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable @Positive(message = "Employee ID must be a positive value") Long employeeId) {
        try {
            Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);
            if (existingEmployee.isPresent()) {
                employeeService.deleteEmployee(employeeId);
                return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


