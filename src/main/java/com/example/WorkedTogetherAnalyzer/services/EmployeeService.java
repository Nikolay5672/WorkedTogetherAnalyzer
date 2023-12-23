package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.models.Employee;
import com.example.WorkedTogetherAnalyzer.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    // Additional custom methods if needed
}

