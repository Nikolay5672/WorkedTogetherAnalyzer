package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedOnId;
import com.example.WorkedTogetherAnalyzer.repositories.WorkedOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkedOnService {

    private final WorkedOnRepository workedOnRepository;

    @Autowired
    public WorkedOnService(WorkedOnRepository workedOnRepository) {
        this.workedOnRepository = workedOnRepository;
    }

    public List<WorkedOn> getAllWorkedOnEntries() {
        return workedOnRepository.findAll();
    }

    public Optional<WorkedOn> getWorkedOnEntryById(WorkedOnId id) {
        return workedOnRepository.findById(id);
    }

    public WorkedOn saveWorkedOnEntry(WorkedOn workedOn) {
        return workedOnRepository.save(workedOn);
    }

    public void deleteWorkedOnEntry(WorkedOnId id) {
        workedOnRepository.deleteById(id);
    }

    // Additional custom methods if needed
}

