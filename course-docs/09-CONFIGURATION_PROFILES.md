# 🚀 Spring Boot Configuration Files (`application.properties` vs `application.yml`)

## 📚 Learning Objectives

By the end of this lesson, students will be able to:

* Understand the purpose of configuration files in Spring Boot.
* Differentiate between `application.properties` and `application.yml`.
* Know when to use which format and how to manage multiple environments.

---

### 1️⃣ Why Configuration Files?

Spring Boot allows **externalized configuration** so we don’t hardcode values in code.
We store environment-specific details (like DB URLs, ports, logging, etc.) in config files.

Spring Boot provides two formats:

* **`application.properties`**
* **`application.yml`**

---

### 2️⃣ `application.properties`

* Default config file.
* Simple **key=value** format.

#### Example:

```properties
# Server configuration
server.port=8081

# Database config
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=admin

# Logging
logging.level.org.springframework=DEBUG
```

---

### 3️⃣ `application.yml`

* Uses **YAML syntax** (indentation-based).
* Cleaner for hierarchical configs.

### Example:

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: admin

logging:
  level:
    org.springframework: DEBUG
```

---

### 4️⃣ How Does Spring Boot Load Them?

* Spring Boot loads config from:

    1. `application.properties` or `application.yml` inside `src/main/resources/`.
    2. External locations (`--spring.config.location`).
    3. Environment variables & system properties.

* If **both exist**, `.yml` can override `.properties` values.

---

### 5️⃣ Properties vs YAML – Comparison

| Feature     | `application.properties`              | `application.yml`                        |
| ----------- | ------------------------------------- | ---------------------------------------- |
| Format      | Key-Value (flat)                      | Hierarchical (nested)                    |
| Readability | Simple for small configs              | Cleaner for complex configs              |
| Repetition  | Prefix repeats for grouped properties | No repetition, nested under one root key |
| Use Case    | Quick start, small apps               | Large apps with structured configs       |

---

### 6️⃣ Profiles – Multiple Environments

We can define configs for **dev, test, prod** profiles.

### In `application.properties`:

```properties
# Default - application.properties
server.port=8080
```
```properties
# Dev profile - application-dev.properties
spring.config.activate.on-profile=dev
server.port=8081
```
```properties
# Prod profile - application-prd.properties
spring.config.activate.on-profile=prod
server.port=9090
```

#### In `application.yml`:

```yaml
server:
  port: 8080

---
spring:
  config:
    activate:
      on-profile: dev
server:
  port: 8081

---
spring:
  config:
    activate:
      on-profile: prod
server:
  port: 9090
```

👉 Run with a profile:

```bash
  mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
OR
```bash
  java -jar target/*.jar --spring.profiles.active=dev
```

---

## ✨ Summary

* `application.properties` → flat, easy for small apps.
* `application.yml` → structured, better for complex apps.
* Both support **profiles** to manage multiple environments (dev/test/prod).

--- 