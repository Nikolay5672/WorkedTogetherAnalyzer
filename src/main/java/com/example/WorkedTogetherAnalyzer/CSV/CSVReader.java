package com.example.WorkedTogetherAnalyzer.CSV;

import com.example.WorkedTogetherAnalyzer.models.WorkedOn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReader {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static List<WorkedOn> readCsvFile(String filePath) {
        List<WorkedOn> workedOnList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Long empId = Long.parseLong(parts[0]);
                Long projectId = Long.parseLong(parts[1]);
                Date startDate = parseDate(parts[2]);
                Date endDate = parseDate(parts[3]);

                WorkedOn workedOn = new WorkedOn(empId, projectId, startDate, endDate);
                workedOnList.add(workedOn);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return workedOnList;
    }

    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

