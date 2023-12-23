package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedTogetherResult;
import com.example.WorkedTogetherAnalyzer.repositories.WorkedOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkedTogetherService {

    private final WorkedOnRepository workedOnRepository;

    @Autowired
    public WorkedTogetherService(WorkedOnRepository workedOnRepository) {
        this.workedOnRepository = workedOnRepository;
    }

    public List<WorkedTogetherResult> findMostTimeWorkedPairs() {
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();
        Map<Long, Map<Long, List<WorkedOn>>> projectEmployeeWorkedEntriesMap = new HashMap<>();

        // Populate projectEmployeeWorkedEntriesMap with worked entries on each project for each employee
        for (WorkedOn entry : allWorkedOnEntries) {
            Long projectId = entry.getProject().getId();
            Long empId = entry.getEmployee().getId();

            projectEmployeeWorkedEntriesMap
                    .computeIfAbsent(projectId, k -> new HashMap<>())
                    .computeIfAbsent(empId, k -> new ArrayList<>())
                    .add(entry);
        }

        List<WorkedTogetherResult> result = new ArrayList<>();

        // Find the pair of employees who worked the most time together on each project
        for (Map.Entry<Long, Map<Long, List<WorkedOn>>> projectEntry : projectEmployeeWorkedEntriesMap.entrySet()) {
            Long projectId = projectEntry.getKey();
            Map<Long, List<WorkedOn>> employeeWorkedEntriesMap = projectEntry.getValue();

            // Find the pair of employees who worked the most time together on the current project
            WorkedTogetherResult maxResult = findMaxWorkedTogetherPair(employeeWorkedEntriesMap, projectId);

            // Add the result to the list
            result.add(maxResult);
        }

        return result;
    }

    private WorkedTogetherResult findMaxWorkedTogetherPair(Map<Long, List<WorkedOn>> employeeWorkedEntriesMap, Long projectId) {
        WorkedTogetherResult maxResult = null;
        long maxDaysWorked = 0;

        List<Long> employeeIds = new ArrayList<>(employeeWorkedEntriesMap.keySet());

        for (int i = 0; i < employeeIds.size() - 1; i++) {
            for (int j = i + 1; j < employeeIds.size(); j++) {
                Long employeeId1 = employeeIds.get(i);
                Long employeeId2 = employeeIds.get(j);

                long totalDaysWorked = computeTotalDaysWorked(employeeWorkedEntriesMap.get(employeeId1), employeeWorkedEntriesMap.get(employeeId2));

                if (totalDaysWorked > maxDaysWorked) {
                    maxDaysWorked = totalDaysWorked;
                    maxResult = new WorkedTogetherResult(employeeId1, employeeId2, projectId, totalDaysWorked);
                }
            }
        }

        return maxResult;
    }

    private long computeTotalDaysWorked(List<WorkedOn> entries1, List<WorkedOn> entries2) {
        long totalDaysWorked = 0;

        for (WorkedOn entry1 : entries1) {
            for (WorkedOn entry2 : entries2) {
                totalDaysWorked += computeDaysWorked(entry1, entry2);
            }
        }

        return totalDaysWorked;
    }

    private long computeDaysWorked(WorkedOn entry1, WorkedOn entry2) {
        LocalDate startDate1 = entry1.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate1 = entry1.getEndDate() != null ? entry1.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.now();

        LocalDate startDate2 = entry2.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate2 = entry2.getEndDate() != null ? entry2.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.now();

        // Find the non-overlapping period
        LocalDate overlapStart = startDate1.isAfter(startDate2) ? startDate1 : startDate2;
        LocalDate overlapEnd = endDate1.isBefore(endDate2) ? endDate1 : endDate2;

        // Calculate the difference in days for the non-overlapping period
        long daysWorked = ChronoUnit.DAYS.between(overlapStart, overlapEnd);

        // Ensure non-negative result
        return Math.max(0, daysWorked);
    }
}
