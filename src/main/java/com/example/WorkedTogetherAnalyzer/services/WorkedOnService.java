package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.CSV.CSVWriter;
import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedOnId;
import com.example.WorkedTogetherAnalyzer.repositories.WorkedOnRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class WorkedOnService {

    private final WorkedOnRepository workedOnRepository;

    @Autowired
    public WorkedOnService(WorkedOnRepository workedOnRepository) {
        this.workedOnRepository = workedOnRepository;
    }
    @PostConstruct
    public void initializeCsvFile() {
        // Load all entries from the database
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();

        // Update the CSV file
        CSVWriter.writeCsvFile("workedOn.csv", allWorkedOnEntries);
    }

    @PreDestroy
    public void updateCsvFileBeforeShutdown() {
        // Load all entries from the database before shutting down
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();

        // Update the CSV file
        CSVWriter.writeCsvFile("workedOn.csv", allWorkedOnEntries);
    }

    public List<WorkedOn> getAllWorkedOnEntries() {
        return workedOnRepository.findAll();
    }

    public Optional<WorkedOn> getWorkedOnEntryById(WorkedOnId id) {
        if (!workedOnRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkedOn entry with id " + id + " not found");
        }
        return workedOnRepository.findById(id);
    }

    public WorkedOn saveWorkedOnEntry(@Valid WorkedOn workedOn) {
        // Save to the database
        WorkedOn savedWorkedOn = workedOnRepository.save(workedOn);

        // Update the CSV file
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();
        CSVWriter.writeCsvFile("workedOn.csv", allWorkedOnEntries);

        return savedWorkedOn;
    }

    public void deleteWorkedOnEntry(WorkedOnId id) {
        if (!workedOnRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkedOn entry with id " + id + " not found");
        }
        workedOnRepository.deleteById(id);

        // Update the CSV file after deletion
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();
        CSVWriter.writeCsvFile("workedOn.csv", allWorkedOnEntries);
    }

    //TODO Additional custom methods if needed
}

