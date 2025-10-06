# 🚀 Data Persistence with Spring Data JPA

---

## ️️ 🏷️ ORM Introduction (JPA & Hibernate)

### 🔹 What is ORM?

Object Relational Mapping (ORM) is a technique to map Java objects to database tables and vice versa. This helps developers work with database data using objects instead of SQL queries.

### 🔹 JPA (Java Persistence API)

* Standard specification for ORM in Java.
* Defines APIs for CRUD operations, queries, and transactions.
* Vendor-neutral.

### 🔹 Hibernate

* Most popular JPA implementation.
* Provides additional features like caching, lazy loading, etc.

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

## 🏷️ Demo: Connect Spring Boot to H2/MySQL

> ✅ `Note:` For working sample refer [here](../src/main/java/com/vednexgen/user)

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

public interface UserRepository extends JpaRepository<User, Long> {}
```

### Step 5: Demo Service

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
