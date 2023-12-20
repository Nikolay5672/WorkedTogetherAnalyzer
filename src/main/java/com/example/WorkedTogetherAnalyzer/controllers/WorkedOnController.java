package com.example.WorkedTogetherAnalyzer.controllers;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedOnId;
import com.example.WorkedTogetherAnalyzer.services.WorkedOnService;
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

    @GetMapping("/get/{empId}/{projectId}")
    public ResponseEntity<WorkedOn> getWorkedOnEntryById(@PathVariable Long empId, @PathVariable Long projectId) {
        WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
        Optional<WorkedOn> workedOnEntry = workedOnService.getWorkedOnEntryById(workedOnId);
        return workedOnEntry.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<WorkedOn> createWorkedOnEntry(@RequestBody WorkedOn workedOn) {
        WorkedOn savedWorkedOn = workedOnService.saveWorkedOnEntry(workedOn);
        return new ResponseEntity<>(savedWorkedOn, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}/{projectId}")
    public ResponseEntity<WorkedOn> updateWorkedOnEntry(
            @PathVariable Long empId, @PathVariable Long projectId, @RequestBody WorkedOn updatedWorkedOn) {
        WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
        Optional<WorkedOn> existingWorkedOn = workedOnService.getWorkedOnEntryById(workedOnId);
        if (existingWorkedOn.isPresent()) {
            updatedWorkedOn.setEmployee(existingWorkedOn.get().getEmployee());
            updatedWorkedOn.setProject(existingWorkedOn.get().getProject());
            WorkedOn savedWorkedOn = workedOnService.saveWorkedOnEntry(updatedWorkedOn);
            return new ResponseEntity<>(savedWorkedOn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{empId}/{projectId}")
    public ResponseEntity<Void> deleteWorkedOnEntry(@PathVariable Long empId, @PathVariable Long projectId) {
        WorkedOnId workedOnId = new WorkedOnId(empId, projectId);
        Optional<WorkedOn> existingWorkedOn = workedOnService.getWorkedOnEntryById(workedOnId);
        if (existingWorkedOn.isPresent()) {
            workedOnService.deleteWorkedOnEntry(workedOnId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

