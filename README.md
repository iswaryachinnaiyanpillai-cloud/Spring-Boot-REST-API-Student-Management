# Student Management REST API

A RESTful Student Management application built using Spring Boot, Hibernate, Spring Data JPA, Maven, and MySQL.

## Features

- Create, read, update, and delete students
- Create, read, update, and delete departments
- One-to-many and many-to-one entity relationships
- Custom database queries using Spring Data JPA
- Input validation for student details
- REST API endpoints for students and departments
- MySQL database persistence

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate ORM
- MySQL
- Maven
- Bean Validation

## API Endpoints

### Students

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/students` | Retrieve students |
| GET | `/api/students/{id}` | Retrieve a student by ID |
| POST | `/api/students?departmentId={id}` | Create a student |
| PUT | `/api/students/{id}?departmentId={id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### Departments

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/departments` | Retrieve departments |
| GET | `/api/departments/{id}` | Retrieve a department by ID |
| POST | `/api/departments` | Create a department |
| PUT | `/api/departments/{id}` | Update a department |
| DELETE | `/api/departments/{id}` | Delete a department |

## Setup Instructions

1. Install Java 17 and MySQL.
2. Create a database named `student_management_db`.
3. Copy `application-example.properties` into `src/main/resources/application.properties`.
4. Update the local database username and password in `application.properties`.
5. Start the application using the Maven wrapper:

   Windows:
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

6. Access the API at `http://localhost:8080`.

## Database Configuration

Keep your actual database credentials in your local `src/main/resources/application.properties` file. This file is excluded from Git and must not be committed.

## Author

Iswarya C.P.
