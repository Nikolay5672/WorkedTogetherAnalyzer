package com.example.WorkedTogetherAnalyzer.services;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;
import com.example.WorkedTogetherAnalyzer.models.WorkedTogetherResult;
import com.example.WorkedTogetherAnalyzer.repositories.EmployeeRepository;
import com.example.WorkedTogetherAnalyzer.repositories.WorkedOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class WorkedTogetherService {

    private final WorkedOnRepository workedOnRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public WorkedTogetherService(WorkedOnRepository workedOnRepository, EmployeeRepository employeeRepository) {
        this.workedOnRepository = workedOnRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<WorkedTogetherResult> findMostTimeWorkedPairs() {
        List<WorkedOn> allWorkedOnEntries = workedOnRepository.findAll();
        Map<Long, Map<Long, Long>> projectEmployeeDaysWorkedMap = new HashMap<>();

        for (WorkedOn entry : allWorkedOnEntries) {
            Long projectId = entry.getProject().getId();
            Long empId = entry.getEmployee().getId();
            Long daysWorked = computeDaysWorked(entry);

            projectEmployeeDaysWorkedMap
                    .computeIfAbsent(projectId, k -> new HashMap<>())
                    .merge(empId, daysWorked, Long::sum);
        }

        List<WorkedTogetherResult> result = new ArrayList<>();

        for (Map.Entry<Long, Map<Long, Long>> projectEntry : projectEmployeeDaysWorkedMap.entrySet()) {
            Long projectId = projectEntry.getKey();
            Map<Long, Long> employeeDaysWorkedMap = projectEntry.getValue();

            // Find the pair of employees who worked the longest on the current project
            Map.Entry<Long, Long> maxEntry = Collections.max(
                    employeeDaysWorkedMap.entrySet(),
                    Map.Entry.comparingByValue()
            );

            Long employee1 = maxEntry.getKey();
            Long totalDaysWorked = maxEntry.getValue();

            // Find the other employee who worked on the project
            employeeDaysWorkedMap.remove(employee1);
            Long employee2 = Collections.max(employeeDaysWorkedMap.entrySet(), Map.Entry.comparingByValue()).getKey();

            result.add(new WorkedTogetherResult(projectId, employee1, employee2, totalDaysWorked));
        }

        return result;
    }

    private long computeDaysWorked(WorkedOn entry) {
        // Convert java.util.Date to LocalDate
        Instant startInstant = entry.getStartDate().toInstant();
        LocalDate startDate = startInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        Instant endInstant = entry.getEndDate() != null
                ? entry.getEndDate().toInstant()
                : LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();

        // Using ChronoUnit to calculate the difference in days
        return ChronoUnit.DAYS.between(startDate, endInstant.atZone(ZoneId.systemDefault()).toLocalDate());
    }

}