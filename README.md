# 🎓 Student Service – Spring Boot Microservice

## 🧩 Overview
The **Student Service** is a Spring Boot RESTful microservice that manages student information — including creation, retrieval, and listing of student records.  
It uses an **H2 in-memory database** for fast testing and **Swagger UI** for live API documentation.

---

##  Tech Stack
- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**
- **OpenAPI (Swagger UI)**
- **Maven**

---

##  How to Run

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/kartikkulkarni940/student-service-api
cd studentservice
```

### 2️⃣ Run the Application
```bash
mvn spring-boot:run
```
or from IntelliJ/Eclipse → run the main class:
```
com.school.studentservice.StudentServiceApplication
```

### 3️⃣ Access the Application
- **Swagger UI:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **H2 Console:** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)

---

##  H2 Database Config

| Property | Value |
|-----------|-------|
| **JDBC URL** | `jdbc:h2:mem:studentdb` |
| **Username** | `sa` |
| **Password** | *(empty)* |
| **Driver Class** | `org.h2.Driver` |

---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/students` | Create a new student |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students` | Get all students |

---

##  Sample Request

**POST /api/students**
```json
{
  "name": "Ravi Kumar",
  "grade": "10",
  "mobileNumber": "9999999999",
  "schoolName": "Delhi Public School"
}
```

**Response**
```json
{
  "id": 1,
  "studentId": "S-F4CC9364",
  "name": "Ravi Kumar",
  "grade": "10",
  "mobileNumber": "9999999999",
  "schoolName": "Delhi Public School"
}
```

---

##  Project Structure
```
studentservice/
 ├── src/main/java/com/school/studentservice/
 │    ├── controller/StudentController.java
 │    ├── service/StudentService.java
 │    ├── dto/StudentRequestDTO.java
 │    ├── dto/StudentResponseDTO.java
 │    └── entity/Student.java
 ├── src/main/resources/
 │    ├── application.yml
 ├── pom.xml
 └── README.md
```

---

##  Build & Test
```bash
mvn clean install
mvn test
```

---

##  Default Port
The service runs on **port 8080** by default.  
You can change it in `application.yml`:
```yaml
server:
  port: 8081
```

---

##  Notes
- Default user credentials for H2:
    - **Username:** `sa`
    - **Password:** *(empty)*
- 
