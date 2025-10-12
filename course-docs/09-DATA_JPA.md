# 🚀 Data Persistence with Spring Data JPA

---

## ️️ 🏷️ ORM Introduction (JPA & Hibernate)

### 🔹 What is ORM?

Object Relational Mapping (ORM) is a technique to map Java objects to database tables and vice versa.  
This helps developers work with databases using object-oriented concepts instead of writing raw SQL queries.

### 🔹 Key Benefits:

- Reduces boilerplate SQL code 
- Easier to maintain and extend 
- Database agnostic development

**Example:**
Java Class ↔ Database Table mapping

| Java Object  | Database Table |
| ------------ | -------------- |
| `Employee`   | `employee`     |
| `employeeId` | `employee_id`  |
| `name`       | `name`         |

---

## 🏷️ What is JPA (Java Persistence API)?

**JPA (Java Persistence API)** is a specification for ORM in Java. It defines how data should be persisted and retrieved in a relational database using Java objects.

**Key Features:**

* Standard API for ORM
* Object-relational mapping
* Entity lifecycle management

---

## 🏷️ What is Hibernate?

**Hibernate** is a widely-used ORM framework that implements the JPA specification. Spring Boot uses Hibernate as the default JPA provider.

**Features of Hibernate:**

* Automatic SQL generation
* Provides caching. lazy/eager loading of data
* Transaction management
* 

---

## 🏷️ Repositories in Spring Data JPA

Spring Data JPA provides ready-to-use repository interfaces for easy database operations.

### 🔹 CrudRepository

* Provides CRUD operations: save, findById, findAll, deleteById.

**Example:**

```java
public interface UserRepository extends CrudRepository<User, Long> {}
```

### 🔹 JpaRepository

* Extends CrudRepository.
* Adds JPA-specific methods like flushing and batch deletes.

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Long> {}
```

---

## 🏷️ How Spring Boot Integrates with JPA

Spring Boot simplifies JPA integration by:

* Providing `spring-boot-starter-data-jpa`
* Auto-configuring a JPA `EntityManagerFactory`
* Automatically scanning for `@Entity` classes
* Integrating Hibernate as the default JPA provider

> ✅ `Note:` For working sample refer [here](../src/main/java/com/vednexgen/jpa/user)

### 📝 Program: Connect Spring Boot to H2/MySQL

### Step 1: Add Dependencies (pom.xml)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Step 2: Configure application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

```yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  h2:
    console:
      enabled: true
```


### Step 3: Define Entity

```java
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // getters and setters
}
```

### Step 4: Create Repository

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
```

### Step 5: User Service

```java
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
```

### Step 6: Controller

```java
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeeController {

    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) {
        User byName = userService.findByName(name);
        return byName;
    }
}
```

### Step 7: Run Application

* Start Spring Boot application.
* Use Postman or browser to test endpoints.
* Access H2 console at `/h2-console`.

---

## ✨ Summary

* ORM allows Java objects to interact with database tables.
* JPA is the standard API for persistence.
* Hibernate is the most common JPA provider.
* Spring Data JPA simplifies CRUD operations with `CrudRepository` and `JpaRepository`.
* Demo shows how to connect Spring Boot to H2/MySQL and persist data.

---

<div>

[![](https://img.shields.io/badge/Prev-⬅️-caddd6?style=for-the-badge&labelColor=caddd6)](08-CONFIGURATION_FILES.md)
&emsp;&emsp;
[![](https://img.shields.io/badge/Next-➡️-caddd6?style=for-the-badge&labelColor=caddd6)](10-EXCEPTIONS_VALIDATION.md)

</div>

[![](https://img.shields.io/badge/Go_Back-🔙-d6cadd?style=for-the-badge&labelColor=d6cadd)](00-TABLE_CONTENT_README.md)

---
