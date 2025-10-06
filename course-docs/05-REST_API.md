# 🚀 Day 5: REST APIs with Spring Boot

## 📚 Learning Objectives

By the end of this lesson, students will be able to:

* Understand what REST APIs are and why they are used.
* Create REST endpoints using Spring Boot annotations.
* Understand HTTP methods and status codes.
* Use request parameters, path variables, and request bodies.
* Build and test a simple CRUD API.

---

### 🔹 What is a REST API?

* **REST (Representational State Transfer)** is an architectural style for building web services, introduced by Roy Fielding in 2000.
* REST APIs use **HTTP methods** to interact with resources.
* Data is usually sent/received in JSON (most common) or XML.

**Example**: A weather mobile app fetching weather data from a server.

#### 🔹 Resource-Oriented Concept

* Everything in REST is treated as a **resource**.
* Each resource is identified by a URL.
  REST Constraints

#### 🔹 A REST API must follow these principles:

1. **Client-Server**: Client and server are independent.
2. **Stateless**: Each request contains all necessary information.
3. **Cacheable**: Responses can be cached for performance.
4. **Uniform Interface**: Standardized way to interact with resources.
5. **Layered System**: API can have multiple layers (load balancers, security layers).

#### 🔹 Common HTTP Methods:

* `GET` → Retrieve data (Safe, Idempotent)
* `POST` → Create new data (Not Idempotent)
* `PUT` → Update existing data (Idempotent)
* `PATCH` → Partially update existing data (Idempotent for the same patch)
* `DELETE` → Delete data (Idempotent)

#### 🔹 Common HTTP Status Codes:

* `200 OK` → Successful request
* `201 Created` → Resource created successfully
* `204 No Content` → Request successful, no response body
* `400 Bad Request` → Invalid request
* `401 Unauthorized` → Authentication required
* `403 Forbidden` → Not allowed to access
* `404 Not Found` → Resource not found
* `500 Internal Server Error` → Something went wrong on server

#### 🔹 Example: Student API endpoints

* `GET /students` → Fetch all students (200 OK)
* `POST /students` → Add a new student (201 Created)
* `GET /students/{id}` → Fetch student by ID (200 OK / 404 Not Found)
* `PUT /students/{id}` → Update student (200 OK / 404 Not Found)
* `DELETE /students/{id}` → Delete student (200 OK / 404 Not Found)

---

### 🏷️ Spring Boot Annotations for REST APIs

* **`@RestController`** → Combines `@Controller` and `@ResponseBody`. Indicates the class handles REST requests, and responses are written directly as JSON/XML.
* **`@RequestMapping`** → Maps HTTP requests to handler classes/methods. Can be applied at class or method level.
* **`@GetMapping`** → Shortcut for `@RequestMapping(method = RequestMethod.GET)`.
* **`@PostMapping`** → Shortcut for `@RequestMapping(method = RequestMethod.POST)`.
* **`@PutMapping`** → Shortcut for `@RequestMapping(method = RequestMethod.PUT)`.
* **`@DeleteMapping`** → Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.
* **`@PathVariable`** → Binds URI template variable to method parameter.
* **`@RequestParam`** → Extracts query parameter from URL.
* **`@RequestBody`** → Binds HTTP request body to a Java object.
* **`@ResponseStatus`** → Explicitly sets HTTP status code for a method.

---

### 🏷️ Creating a Controller in Spring Boot

> ✅ `Note:` For working sample refer [here](../src/main/java/com/vednexgen/student)

#### Step 1: Create a `Student` model

```java
package com.example.demo.model;

public class Student {
    private int id;
    private String name;
    private String email;

    // Constructor
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

#### Step 2: Create a `StudentController`

```java
package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Alice", "alice@example.com"));
        students.add(new Student(2, "Bob", "bob@example.com"));
    }

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students); // 200 OK
    }

    // GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST - Add new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student); // 201 Created
    }

    // PUT - Update student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(updatedStudent.getName());
                s.setEmail(updatedStudent.getEmail());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE - Remove student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Student removed with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}
```

---

### 🏷️ Testing the API

Use **Postman** or browser:

* `GET http://localhost:8080/students` → Returns all students (200 OK)
* `GET http://localhost:8080/students/1` → Returns student with ID 1 (200 OK or 404 Not Found)
* `POST http://localhost:8080/students` → Add student (201 Created)

```json
{
  "id": 3,
  "name": "Charlie",
  "email": "charlie@example.com"
}
```

* `PUT http://localhost:8080/students/1` → Update student with ID 1 (200 OK or 404 Not Found)
* `DELETE http://localhost:8080/students/2` → Delete student with ID 2 (200 OK or 404 Not Found)

---

### 🏷️ Flow Diagram

```
Client → HTTP Request → Spring Boot Controller → Business Logic → Response (JSON + Status Code)
```

---

## ✨ Summary

* Learned what REST APIs are and HTTP method types.
* Explored HTTP status codes for different scenarios.
* Understood key Spring annotations (`@RestController`, `@GetMapping`, `@PostMapping`, etc.).
* Built a CRUD Student API with proper status codes.
* Tested APIs using Postman/browser.

---
