![Java](https://img.shields.io/badge/Java-21-ED8B00)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F)
![Tests](https://img.shields.io/badge/Tests-Passing-brightgreen)

```
# 📘 Task S4.01 – Spring Boot REST API (User API)

## 🎯 Overview

This document summarises the work completed for **Task S4.01**.  
The objective was to build a **REST API using Spring Boot**, return **JSON responses**, implement **in-memory user management**, apply **layered architecture**, and verify behaviour through **automated testing**.

---

## 🧱 Project Setup

The project was generated using **start.spring.io** with the following configuration:

- 🔧 Spring Boot 3.x  
- ☕ Java 21  
- 📦 Maven  
- 📚 Dependencies: Spring Web, DevTools  

Application configuration:

```

spring.application.name=UserApi
server.port=9000

```

Base package:

```

cat.itacademy.s04.t01.userapi

````

---

## ⭐ Level 1 – Health Check Endpoint

A basic endpoint was created to verify that the application starts correctly.

### Implementation

- 📁 Package: `controllers`
- 🧭 Controller: `HealthController`
- 🔌 Endpoint: `GET /health`
- 📤 Response:
  ```json
  { "status": "OK" }
````

The response is returned using a dedicated record:

```
HealthResponse(String status)
```

### Verification

* Browser request
* Postman
* Controller test using MockMvc

---

## 🚀 Running the Application

The application can be packaged and executed as a JAR file:

```bash
mvn clean package
java -jar target/userapi-0.0.1-SNAPSHOT.jar
```

The API is available at:

```
http://localhost:9000
```

---

## ⭐⭐ Level 2 – User Management (In-Memory)

A user management system was implemented without a database.
User data is stored temporarily using an **in-memory list**.

### Domain Model

```
User {
  UUID id
  String name
  String email
}
```

---

## 🌐 REST Endpoints

| Method | Endpoint     | Description                    |
| -----: | ------------ | ------------------------------ |
|    GET | /health      | Application health check       |
|    GET | /users       | Retrieve all users             |
|   POST | /users       | Create a new user              |
|    GET | /users/{id}  | Retrieve user by ID            |
|    GET | /users?name= | Search user by name (optional) |

---

## ⚙️ Layered Architecture

The application follows a clean layered structure:

```
controllers → services → repositories → models
```

### Controller Layer

* `HealthController`
* `UserController`

Responsibilities:

* Handle HTTP requests
* Map endpoints
* Delegate logic to the service layer

---

### Service Layer

Interface:

```
UserService
```

Implementation:

```
UserServiceImpl
```

Responsibilities:

* Business logic
* UUID generation
* Validation rules
* Exception handling

Business rule implemented:

* A user email must be unique
* If the email already exists, an exception is thrown

---

### Repository Layer

Interface:

```
UserRepository
```

Implementation:

```
InMemoryUserRepository
```

Responsibilities:

* Store users in an in-memory list
* Search by ID and name
* Validate email uniqueness

---

## ⚠️ Exception Handling

Custom exceptions were implemented:

* `EmailAlreadyExistsException`
* `UserNotFoundException`

A global exception handler maps exceptions to HTTP responses:

| Exception                   | HTTP Status   |
| --------------------------- | ------------- |
| EmailAlreadyExistsException | 409 CONFLICT  |
| UserNotFoundException       | 404 NOT FOUND |

Error responses are returned in JSON format:

```json
{ "error": "message" }
```

---

## 🧪 Testing Strategy

### Controller Tests

* `@WebMvcTest`
* `MockMvc`
* `ObjectMapper`
* `@MockitoBean`

Tested behaviours:

* Health endpoint returns OK
* Empty user list on startup
* User creation returns generated UUID

---

### Service Unit Tests

* Mockito
* `@ExtendWith(MockitoExtension.class)`
* `@Mock` and `@InjectMocks`

Verified behaviours:

* Exception thrown when email already exists
* User saved correctly when email is unique
* User retrieval by ID

---

### Context Test

* `@SpringBootTest`
* Application context loads successfully

---

## 📌 Final Conclusion

This task allowed me to practise:

* REST API fundamentals with Spring Boot
* JSON request and response handling
* Controller, Service, and Repository separation
* In-memory data management
* Custom exception handling
* Automated testing with MockMvc and Mockito
* Running a Spring Boot application as an executable JAR

The application executed correctly, followed clean architecture principles, and **all tests passed successfully**.

## 🛠️ Installation
1. Clone the repository:  
   ```bash
   git clone [https://github.com/yvangabrieli/S3.02.Patterns_2](https://github.com/yvangabrieli/S4.01.SpringBoot.git)

```

