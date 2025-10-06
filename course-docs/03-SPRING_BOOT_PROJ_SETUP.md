# 🚀 Spring Boot Project Setup

## 📚 Learning Objectives

By the end of this section, you will be able to:

* Set up a new Spring Boot project using **Spring Initializr**.
* Understand the **anatomy** of a Spring Boot application.
* Run a Spring Boot application and test it in a browser/Postman.

---

### 1️⃣ Why Do We Need Project Setup?

Before we can build APIs or use Spring features, we need a **Spring Boot project structure**.
Spring Boot simplifies project setup with:

* **Spring Initializr** – quick project generator.
* **Maven/Gradle** – dependency management.
* **Embedded Tomcat** – no need to install external servers.

---

### 2️⃣ Tools Required

* **JDK (Java 8/11/17)** – depending on your environment.
* **IDE** – IntelliJ IDEA / Eclipse / VS Code.
* **Maven/Gradle** – comes with most IDEs.
* **Spring Initializr** – [start.spring.io](https://start.spring.io).

---

### 3️⃣ Creating a Spring Boot Project (Spring Initializr)

#### Steps:

1. Go to [https://start.spring.io](https://start.spring.io).
2. Choose project metadata:

    * Project: Maven Project
    * Language: Java
    * Spring Boot: (latest stable version)
    * Group: `com.example`
    * Artifact: `demo`
    * Packaging: Jar
    * Java: 17 (recommended)
3. Add dependencies:

    * **Spring Web** (to build REST APIs).
    * (Optional) **Spring Boot DevTools** (auto restart).
4. Click **Generate** → Download `.zip` → Extract → Open in IDE.

---

### 4️⃣ Anatomy of a Spring Boot Project

When the project opens, you’ll see:

```
demo/
 ├── src/main/java/com/example/demo
 │    └── DemoApplication.java   <-- Main class with @SpringBootApplication
 ├── src/main/resources
 │    ├── application.properties <-- Configuration file
 │    └── static/                <-- Static web resources
 │    └── templates/             <-- Thymeleaf templates
 ├── pom.xml (if Maven)
```

#### Important Files:

* **`DemoApplication.java`** → Entry point, contains `main()` method.
* **`application.properties`** → For configurations like DB URL, port, logging.
* **`pom.xml` / `build.gradle`** → Dependency management.

---

### 5️⃣ Running the Application

Run the application:

* Using IDE: Right-click `DemoApplication.java` → **Run**.
* Using command line:

  ```bash
  mvn spring-boot:run
  ```
* By default, app runs on port **8080** → Open browser: [http://localhost:8080](http://localhost:8080).

---

### 6️⃣ First Demo API

Inside `src/main/java/com/example/demo/` create a controller:

```java
package com.vednexgen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}
```

#### Test:

* Open [http://localhost:8080/hello](http://localhost:8080/hello) → You should see:

  ```
  Hello, Spring Boot!
  ```

---

### 7️⃣ Flow Diagram

```
Browser → Request (/hello) → DispatcherServlet → HelloController → Response ("Hello, Spring Boot!")
```

---

### ✅ Recap

* Learned **how to create a Spring Boot project** using Spring Initializr.
* Understood **project structure** and important files.
* Built and ran the **first Spring Boot REST API**.

---
