Employee Collaboration Analyzer
Overview
The Employee Collaboration Analyzer is a Spring Boot application designed to analyze and track the collaboration between employees on various projects. The system reads data from a CSV file containing information about employee IDs, project IDs, and the duration of their collaboration on specific projects. It then calculates and presents insights into the longest collaborative efforts and durations.

Features
1. CSV Data Parsing
The application supports loading input data from a CSV file with a flexible format, allowing for multiple date formats.

2. Collaboration Analysis
The system identifies pairs of employees who have worked together for the longest period and provides the duration of their collaboration on each project.

3. Persistence (Optional)
An optional feature includes the persistence of data, allowing for the storage and retrieval of collaboration records even after the application is restarted.

4. CRUD for Employees (Optional)
Another optional feature provides basic CRUD (Create, Read, Update, Delete) operations for employee data, enhancing the management capabilities of the application.

Technology Stack
Java: The application is built using the Java programming language.
Spring Boot: Leveraging the Spring Boot framework for creating robust and scalable applications.
Spring Data JPA: Utilizing Spring Data JPA for easy interaction with the database.
MySQL Database: Configured to work with a MySQL database for data storage.
CSV Parsing: OpenCSV library is employed for parsing CSV files.
Maven/Gradle: Using Maven or Gradle as build tools for managing dependencies and building the project.
