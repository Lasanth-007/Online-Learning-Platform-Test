
# 🎓Online Learning Platform

This project demonstrates modern Spring Boot development practices including proper entity relationships, polymorphic inheritance, DTO mapping, and clean architecture.

## Features

- 👤 User Management with roles (Student, Instructor)
- 📚 Course Management with instructor assignment
- 📽️ Polymorphic Resources (Video, Audio, PDF)
- 📝 Student Enrollment system with composite keys
- 🕒 Audit Fields (createdTime, updatedTime)

## Technologies Used

- **Backend**: Spring Boot 4.0.5
- **Java**: 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA + Hibernate
- **Mapping**: MapStruct
- **Build Tool**: Maven
- **Validation**: Jakarta Validation

## 📂 Project Structure

```text
src/main/java/learn/onlinelearningplatform/
├── 📁 controller/  # REST API Endpoints & Request Handling
├── 📁 dto/         # Data Transfer Objects for API Requests/Responses
├── 📁 entity/      # JPA/Hibernate Database Models
├── 📁 mapper/      # MapStruct Interfaces for DTO-Entity Conversion
├── 📁 repository/  # Spring Data JPA Repositories (Data Access Layer)
├── 📁 service/     # Business Logic & Service Interfaces
└── 📄 OnlineLearningPlatformApplication.java  # Main Application Entry Point
```

## Main Entities

- **User** - Students and Instructors
- **Course** - Learning courses
- **Section** - Chapters inside a course
- **Lecture** - Individual lessons
- **Resource** - Polymorphic content (Video, Audio, PDF)
- **Enrollment** - Join entity between User and Course
- **BaseEntity** - Common audit fields



## API Endpoints

### 👤 Users
- `POST /users` - Create user
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `GET /users/email/{email}` - Get user by email
- `PUT /users/{id}` - Update user (Full update)
- `PATCH /users/{id}` - Patch user (Partial update)
- `DELETE /users/{id}` - Delete user

### 📚 Courses
- `POST /api/courses` - Create course
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course by ID
- `GET /api/courses/instructor/{instructorId}` - Get courses by instructor

### 📑 Sections
- `POST /api/sections` - Create section
- `GET /api/sections/course/{courseId}` - Get sections by course ID
- `GET /api/sections/{id}` - Get section by ID
- `PUT /api/sections/{id}` - Update section
- `PATCH /api/sections/{id}` - Patch section
- `DELETE /api/sections/{id}` - Delete section

### 👨‍🏫 Lectures
- `POST /api/lectures` - Create lecture
- `GET /api/lectures/{id}` - Get lecture by ID
- `PUT /api/lectures/{id}` - Update lecture
- `PATCH /api/lectures/{id}` - Patch lecture
- `DELETE /api/lectures/{id}` - Delete lecture

### 🧐 Enrollments
- `POST /api/enrollments` - Enroll user in course
- `GET /api/enrollments/user/{userId}` - Get user's enrollments
- `GET /api/enrollments/course/{courseId}` - Get course's enrollments
- `DELETE /api/enrollments/user/{userId}/course/{courseId}` - Unenroll

## ⚙️ Setup Instructions

1. Clone the repository
2. Create a PostgreSQL database named `learning_platform`
3. Update `application.yaml` with your database credentials
4. Run the application
5. Once the application is running, you can explore and test the APIs 
   through the interactive Swagger UI:
- Swagger UI: http://localhost:8080/swagger-ui.html

The application will automatically create the necessary tables using JPA.

## Tech Highlights

- Single Table Inheritance for Resources (Video, Audio, PDF)
- Composite primary key using `@EmbeddedId` + `@MapsId`
- Proper DTO separation (Create vs Response)
- MapStruct for entity-DTO mapping
- Lombok for reducing boilerplate
- Clean layered architecture
