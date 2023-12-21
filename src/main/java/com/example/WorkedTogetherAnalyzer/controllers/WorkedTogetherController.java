package com.example.WorkedTogetherAnalyzer.controllers;

import com.example.WorkedTogetherAnalyzer.models.WorkedTogetherResult;
import com.example.WorkedTogetherAnalyzer.services.WorkedTogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/worked-together")
public class WorkedTogetherController {

    private final WorkedTogetherService workedTogetherService;

    @Autowired
    public WorkedTogetherController(WorkedTogetherService workedTogetherService) {
        this.workedTogetherService = workedTogetherService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<WorkedTogetherResult>> getMostTimeWorkedPairs() {
        List<WorkedTogetherResult> result = workedTogetherService.findMostTimeWorkedPairs();

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}

