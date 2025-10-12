# 🚀 REST API Documentation Using Swagger Open API

## 📚 Introduction

Documentation is an essential part of any REST API. It helps developers understand available endpoints, request/response structures, parameters, and status codes. In Spring Boot, we commonly use **Swagger (OpenAPI)** to automatically generate interactive documentation for our APIs.

---

## 📚 What is Swagger / OpenAPI?

* **OpenAPI Specification (OAS):** A standard way to describe REST APIs.
* **Swagger:** A set of open-source tools that implement the OpenAPI specification.

    * **Swagger UI:** Interactive web interface to explore and test APIs.
    * **Swagger Editor:** Tool to design OpenAPI specifications.
    * **Swagger Codegen:** Generates server stubs and client SDKs from API definitions.

---

## 📚 Adding Swagger to Spring Boot

### 🏷️ Step 1: Add Dependency (Maven)

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.13</version>
</dependency>
```
---

### 🏷️ Step 2: Run the Application

Start your Spring Boot application and open the following URL:

```
http://localhost:8080/swagger-ui.html
```

This will open the **Swagger UI**, automatically documenting all your REST endpoints.

---

## 📚 Customizing Swagger Information

> ✅ `Note:` For working sample refer SwaggerConfigs.java file [here](../src/main/java/com/vednexgen/config)

You can customize the OpenAPI documentation using the `@OpenAPIDefinition` annotation.

```java
package com.vednexgen.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Course API",
                version = "1.0",
                description = "API documentation for Spring Boot Course API"
        )
)
public class SwaggerConfigs {
}
```

---

## 📝 Program: Swagger UI in Action

**Controller Example:**

```java
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private Map<Integer, String> employees = new HashMap<>();

    @PostMapping
    public String addEmployee(@RequestParam String name) {
        int id = employees.size() + 1;
        employees.put(id, name);
        return "Employee added with ID: " + id;
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable int id) {
        return employees.getOrDefault(id, "Employee not found");
    }

    @GetMapping
    public Map<Integer, String> getAllEmployees() {
        return employees;
    }
}
```

When you run the application and open Swagger UI, you’ll see all endpoints under the **EmployeeController** automatically listed with request methods, parameters, and sample responses.

---

### 🧠 Additional Customization

You can also customize global settings using application.yml:

```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
    tags-sorter: alpha
```
---

## 📌 Key Notes

#### Features of Swagger UI

* Interactive testing (try endpoints directly from browser)
* Automatically displays all request/response models
* Supports authentication headers
* Descriptions and annotations for clear documentation

---

## ✨ Summary

| Concept                | Description                                |
| ---------------------- | ------------------------------------------ |
| **Swagger/OpenAPI**    | Standard for documenting APIs              |
| **Swagger UI**         | Interactive interface for testing APIs     |
| **Springdoc OpenAPI**  | Integration library for Spring Boot        |
| **@OpenAPIDefinition** | Customizes title, description, and version |
| **application.yml**    | Used for Swagger path and UI customization |

---

<div>

[![](https://img.shields.io/badge/Prev-⬅️-caddd6?style=for-the-badge&labelColor=caddd6)](16-UNIT_AND_INTEGRTION_TEST.md)
&emsp;&emsp;
[![](https://img.shields.io/badge/Next-➡️-caddd6?style=for-the-badge&labelColor=caddd6)](18-SECURITY_INTEGRATION_WITH_SWAGGER.md)

</div>

[![](https://img.shields.io/badge/Go_Back-🔙-d6cadd?style=for-the-badge&labelColor=d6cadd)](00-TABLE_CONTENT_README.md)

---