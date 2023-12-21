package com.example.WorkedTogetherAnalyzer.CSV;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CSVWriter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void writeCsvFile(String filePath, List<WorkedOn> workedOnList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (WorkedOn workedOn : workedOnList) {
                String line = workedOn.getEmpId() + ","
                        + workedOn.getProjectId() + ","
                        + dateFormat.format(workedOn.getStartDate()) + ","
                        + dateFormat.format(workedOn.getEndDate()) + "\n";
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

