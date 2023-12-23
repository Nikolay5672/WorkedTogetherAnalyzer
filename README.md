Worked Together Analyzer
Overview
The Worked Together Analyzer is a Spring Boot application designed to analyze and track the collaboration between employees on various projects. The system reads data from a CSV file containing information about employee IDs, project IDs, and the duration of their collaboration on specific projects. It then calculates and presents insights into the longest collaborative efforts and durations. There is also a mySQL database storing all the data.

Features
1. CSV Data Parsing
The application supports loading input data from a CSV file with a flexible format, allowing for multiple date formats.

2. Collaboration Analysis
The system identifies pairs of employees who have worked together for the longest period on each project and provides the duration of their collaboration.

3. Persistence
An optional feature includes the persistence of data, allowing for the storage and retrieval of collaboration records even after the application is restarted.

4. CRUD for Employees, Projects, WorkedOn and WorkedTogether (Optional)
Another optional feature provides basic CRUD (Create, Read, Update, Delete) operations for enhancing the management capabilities of the application.

Technology Stack
Java: The application is built using the Java programming language.
Spring Boot: Leveraging the Spring Boot framework for creating robust and scalable applications.
Spring Data JPA: Utilizing Spring Data JPA for easy interaction with the database.
MySQL Database: Configured to work with a MySQL database for data storage.
CSV Parsing: OpenCSV library is employed for parsing CSV files.
Maven/Gradle: Using Maven or Gradle as build tools for managing dependencies and building the project.
