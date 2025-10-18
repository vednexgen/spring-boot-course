# 🚀 Exception Handling & Validation in Spring Boot

## 🏷️ Introduction

Exception handling and validation are crucial in building robust Spring Boot applications.  
They help manage errors gracefully and ensure that user input is correct before processing.

> ✅ `Note:` For working sample refer [here](../src/main/java/com/vednexgen/exception_handling)

---

## 🏷️ Exception Handling in Spring Boot

Spring Boot provides multiple ways to handle exceptions. The two most important mechanisms are:

### 🔹 `@ExceptionHandler`

* Used inside a controller to handle specific exceptions.
* Maps exceptions to custom response objects.

**Example:**

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeDTO getUser(@PathVariable int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
        return new EmployeeDTO("John", "john@vednexgen.com", 23);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidId(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
```

---

### 🔹 `@RestControllerAdvice`

* A global exception handler for all controllers.
* Centralizes error handling logic.

**Example:**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Something went wrong: " + ex.getMessage());
    }
}
```

---

## 🏷️ Validation in Spring Boot

- Spring Boot integrates with **Hibernate Validator** (JSR-380/JSR-303 implementation) to validate user input.  
- Spring Boot does not bundle Hibernate Validator directly in the core — instead, it autoconfigures it when you include the `spring-boot-starter-validation` dependency.

### 🔹 Common Validation Annotations

* `@NotNull` → Field cannot be null.
* `@Size(min, max)` → Validates length of String, Collection, etc.
* `@Min` / `@Max` → Validates numeric values.
* `@Email` → Checks for valid email format.
* `@Pattern` → Validates regex patterns.

### 🔹 Example with `@Valid`

```java
@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    // getters & setters
}
```

```java
@RestController
@RequestMapping("/users")
public class EmployeeController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody EmployeeDTO user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return ResponseEntity.ok("User created successfully");
    }
}
```

---

## 🏷️ Demo: Validate User Input & Return Error Responses

**Request:**

```http
POST /users
Content-Type: application/json

{
  "name": "",
  "email": "invalidEmail",
  "age": 15
}
```

**Response:**

```json
{
  "errors": [
    "Name must be between 2 and 20 characters",
    "Invalid email format",
    "Age must be at least 18"
  ]
}
```

---

## 🏷️ Benefits

✅ Centralized error handling with `@RestControllerAdvice`  
✅ Cleaner controllers with `@ExceptionHandler`  
✅ Ensures data integrity with validation  
✅ Better user experience with meaningful error messages  

---

## ✨ Summary

* Use `@ExceptionHandler` for controller-specific exceptions.
* Use `@RestControllerAdvice` for global exception handling.
* Use `@Valid` and validation annotations for input validation.
* Always return **clear error responses** for client understanding.

---

<div>

[![](https://img.shields.io/badge/Prev-⬅️-caddd6?style=for-the-badge&labelColor=caddd6)](09-DATA_JPA.md)
&emsp;&emsp;
[![](https://img.shields.io/badge/Next-➡️-caddd6?style=for-the-badge&labelColor=caddd6)](11-SPRING_CONFIGS.md)

</div>

[![](https://img.shields.io/badge/Go_Back-🔙-d6cadd?style=for-the-badge&labelColor=d6cadd)](00-TABLE_CONTENT_README.md)

---
