# 🚀 Spring Boot DevTools

## 📚 Overview

Spring Boot DevTools is a set of developer tools that improves the development experience by enabling live reload and automatic application restart when code changes.

---

### 🏷️ Features

* **Automatic Restart:** Restarts the application whenever files on the classpath change.
* **Live Reload:** Automatically refreshes the browser when resources change.
* **Configurations:** Enables certain development-only properties for faster iteration.

---

### 🏷️ Add Dependency

Add the following dependency to your `pom.xml` for Maven projects:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```
---

### 🏷️ Enable Hot Reload

* By default, DevTools watches for classpath changes and restarts the application automatically.
* Live reload is supported via browsers with the LiveReload plugin.
* Ensure `spring.devtools.restart.enabled=true` (default) in `application.properties` or `application.yml`.

---

### 🏷️ Demo Example

1. Run your Spring Boot application.
2. Make changes to a controller or resource.
3. Observe:

    * Application automatically restarts.
    * Browser refreshes automatically if LiveReload is configured.
---

## 📌 Key Notes

* DevTools should only be used during development, not in production.
* Avoid including DevTools in production dependencies (use `optional` for Maven or `developmentOnly` for Gradle).

---

## ✨ Summary

Spring Boot DevTools significantly speeds up the development cycle by providing automatic restart and live reload, allowing developers to see changes immediately without manual intervention.

---
<div>

[![](https://img.shields.io/badge/Prev-⬅️-caddd6?style=for-the-badge&labelColor=caddd6)](18-SECURITY_INTEGRATION_WITH_SWAGGER.md)

</div>

[![](https://img.shields.io/badge/Go_Back-🔙-d6cadd?style=for-the-badge&labelColor=d6cadd)](00-TABLE_CONTENT_README.md)

---
