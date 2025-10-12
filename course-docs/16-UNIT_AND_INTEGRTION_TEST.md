# 🚀 Unit & Integration Testing in Spring Boot

## 📚 Introduction

Testing is a crucial part of software development. In Spring Boot, we can perform **unit testing** and **integration testing** using tools like **JUnit 5**, **Mockito**, and **Spring Boot Test framework**.

This topic covers:

* Unit Testing with JUnit & Mockito
* Integration Testing with @SpringBootTest and MockMVC

---

### 🏷️ 1. Unit Testing

#### 🔹 What is Unit Testing?

Unit Testing focuses on testing **individual components or methods** in isolation.

✅ It ensures that a single function works as expected.
❌ It doesn’t involve the database, HTTP calls, or external systems.

---

#### 🔹 Tools Used

| Tool                   | Purpose                                      |
| ---------------------- | -------------------------------------------- |
| **JUnit 5**            | Framework for writing and running unit tests |
| **Mockito**            | Used for mocking dependencies                |
| **AssertJ / Hamcrest** | Provides fluent assertion syntax             |

---

#### 🔹 Program: Unit Test for EmployeeService

#### Step 1️⃣ — EmployeeService Class

```java
package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }
}
```

#### Step 2️⃣ — Writing Unit Test using Mockito

```java
package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = Arrays.asList(new Employee(1, "Atrangi"));
        when(repository.findAll()).thenReturn(mockList);

        List<Employee> result = service.getAllEmployees();

        assertEquals(1, result.size());
        assertEquals("Atrangi", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(2, "John");
        when(repository.save(emp)).thenReturn(emp);

        Employee result = service.addEmployee(emp);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(repository).save(emp);
    }
}
```

---

### 📌 Key Points

* Use **Mockito** to mock repository or service dependencies.
* Use **@InjectMocks** to inject mocks into the test class.
* Use **verify()** to confirm method invocations.

---

### 🏷️ 2. Integration Testing

#### 🔹 What is Integration Testing?

Integration testing checks whether multiple layers (Controller, Service, Repository) work together as expected.

It usually runs in a **Spring context**, often using **in-memory DB** like H2.

---

#### 🔹 Tools Used

| Tool                      | Purpose                                           |
| ------------------------- | ------------------------------------------------- |
| **@SpringBootTest**       | Loads full Spring context for testing             |
| **MockMvc**               | Simulates HTTP requests without starting a server |
| **@AutoConfigureMockMvc** | Auto-configures MockMvc instance                  |
| **H2 Database**           | In-memory DB used for testing persistence         |

---

#### 🔹 Program: Test REST Controller using MockMVC

#### Step 1️⃣ — EmployeeController Class

```java
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }
}
```

#### Step 2️⃣ — Integration Test using MockMVC

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("Atrangi");

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Atrangi"));
    }

    @Test
    void testGetEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk());
    }
}
```

---

## 🏷️ Difference Between Unit and Integration Testing

| Feature      | Unit Testing            | Integration Testing                         |
| ------------ | ----------------------- | ------------------------------------------- |
| Scope        | Individual method/class | Multiple layers (Controller → Service → DB) |
| Dependencies | Mocked using Mockito    | Real Spring context                         |
| Speed        | Very fast               | Slower                                      |
| Annotations  | `@Mock`, `@InjectMocks` | `@SpringBootTest`, `@AutoConfigureMockMvc`  |

---

## 📌 Key Notes

✅ Write unit tests for **business logic** first.
✅ Keep tests **independent** and **repeatable**.
✅ Use **descriptive test names** (e.g., `shouldReturnEmployeeListWhenGetAllIsCalled`).
✅ For integration tests, prefer **H2** or embedded databases.
✅ Run tests automatically with **CI/CD pipelines**.

---

## ✨ Summary

| Concept             | Description                                  |
| ------------------- | -------------------------------------------- |
| **JUnit 5**         | Framework for unit testing                   |
| **Mockito**         | For mocking dependencies                     |
| **@SpringBootTest** | Loads Spring context for integration testing |
| **MockMVC**         | Simulates REST calls without server          |

---
