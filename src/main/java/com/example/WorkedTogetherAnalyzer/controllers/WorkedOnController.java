package com.example.WorkedTogetherAnalyzer.controllers;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedOnId;
import com.example.WorkedTogetherAnalyzer.services.WorkedOnService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workedOn")
public class WorkedOnController {

    private final WorkedOnService workedOnService;

    @Autowired
    public WorkedOnController(WorkedOnService workedOnService) {
        this.workedOnService = workedOnService;
    }

    @GetMapping("/getAll")
    public List<WorkedOn> getAllWorkedOnEntries() {
        return workedOnService.getAllWorkedOnEntries();
    }

    @GetMapping("/{empId}/{projectId}")
    public ResponseEntity<Object> getWorkedOnEntryById(
            @PathVariable @Positive(message = "Employee ID must be a positive value") Long empId,
            @PathVariable @Positive(message = "Project ID must be a positive value") Long projectId) {
        WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
        Optional<WorkedOn> workedOnEntry = workedOnService.getWorkedOnEntryById(workedOnId);

        return workedOnEntry.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("WorkedOn entry not found"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createWorkedOnEntry(@RequestBody @Valid WorkedOn workedOn) {
        try {
            WorkedOn savedWorkedOn = workedOnService.saveWorkedOnEntry(workedOn);
            return new ResponseEntity<>(savedWorkedOn, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{empId}/{projectId}")
    public ResponseEntity<?> updateWorkedOnEntry(
            @PathVariable @Positive(message = "Employee ID must be a positive value") Long empId,
            @PathVariable @Positive(message = "Project ID must be a positive value") Long projectId,
            @RequestBody @Valid WorkedOn updatedWorkedOn) {
        try {
            WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
            Optional<WorkedOn> existingWorkedOn = workedOnService.getWorkedOnEntryById(workedOnId);
            if (existingWorkedOn.isPresent()) {
                updatedWorkedOn.setEmployee(existingWorkedOn.get().getEmployee());
                updatedWorkedOn.setProject(existingWorkedOn.get().getProject());
                WorkedOn savedWorkedOn = workedOnService.saveWorkedOnEntry(updatedWorkedOn);
                return new ResponseEntity<>(savedWorkedOn, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("WorkedOn entry not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{empId}/{projectId}")
    public ResponseEntity<?> deleteWorkedOnEntry(
            @PathVariable @Positive(message = "Employee ID must be a positive value") Long empId,
            @PathVariable @Positive(message = "Project ID must be a positive value") Long projectId) {
        try {
            WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
            Optional<WorkedOn> existingWorkedOn = workedOnService.getWorkedOnEntryById(workedOnId);
            if (existingWorkedOn.isPresent()) {
                workedOnService.deleteWorkedOnEntry(workedOnId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("WorkedOn entry not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

