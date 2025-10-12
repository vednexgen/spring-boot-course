# 🚀 Detailed Explanation of Spring Boot REST Annotations

## 1️⃣ `@RestController`

* **Definition:** Combination of `@Controller` and `@ResponseBody`.
* **Purpose:** Indicates the class handles RESTful requests and returns data directly as JSON or XML without rendering a view.
* **Example:**

```java
@RestController
public class MyController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

---

## 2️⃣ `@RequestMapping`

* **Definition:** Maps HTTP requests to controller classes/methods.
* **Purpose:** Can define URL paths, HTTP methods, headers, and consumes/produces data types.
* **Example:**

```java
@RequestMapping(value = "/students", method = RequestMethod.GET)
public List<Student> getStudents() {
    return students;
}
```

---

## 3️⃣ `@GetMapping`

* **Definition:** Shortcut for `@RequestMapping(method = RequestMethod.GET)`.
* **Purpose:** Handles HTTP GET requests.
* **Example:**

```java
@GetMapping("/students")
public List<Student> getStudents() {
    return students;
}
```

---

## 4️⃣ `@PostMapping`

* **Definition:** Shortcut for `@RequestMapping(method = RequestMethod.POST)`.
* **Purpose:** Handles HTTP POST requests, often used for creating new resources.
* **Example:**

```java
@PostMapping("/students")
public Student addStudent(@RequestBody Student student) {
    students.add(student);
    return student;
}
```

---

## 5️⃣ `@PutMapping`

* **Definition:** Shortcut for `@RequestMapping(method = RequestMethod.PUT)`.
* **Purpose:** Handles HTTP PUT requests, used to update resources fully.
* **Example:**

```java
@PutMapping("/students/{id}")
public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
    // update logic
}
```

---

## 6️⃣ `@DeleteMapping`

* **Definition:** Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.
* **Purpose:** Handles HTTP DELETE requests.
* **Example:**

```java
@DeleteMapping("/students/{id}")
public String deleteStudent(@PathVariable int id) {
    // delete logic
    return "Deleted";
}
```

---

## 7️⃣ `@PathVariable`

* **Definition:** Binds a URI template variable to a method parameter.
* **Purpose:** Extracts dynamic values from the URL.
* **Example:**

```java
@GetMapping("/students/{id}")
public Student getStudent(@PathVariable int id) {
    // logic to find student
}
```

---

## 8️⃣ `@RequestParam`

* **Definition:** Extracts query parameters from the URL.
* **Purpose:** Accepts input data from query string parameters.
* **Example:**

```java
@GetMapping("/students")
public List<Student> getStudentsByName(@RequestParam String name) {
    // filter students by name
}
```

---

## 9️⃣ `@RequestBody`

* **Definition:** Binds HTTP request body to a Java object.
* **Purpose:** Automatically converts JSON request body to Java objects.
* **Example:**

```java
@PostMapping("/students")
public Student addStudent(@RequestBody Student student) {
    // add logic
}
```

---

## 🔟 `@ResponseStatus`

* **Definition:** Explicitly sets the HTTP status code for a method.
* **Purpose:** Allows you to return custom HTTP status codes.
* **Example:**

```java
@ResponseStatus(HttpStatus.CREATED)
@PostMapping("/students")
public Student addStudent(@RequestBody Student student) {
    return student;
}
```

---

<div>

[![](https://img.shields.io/badge/Prev-⬅️-caddd6?style=for-the-badge&labelColor=caddd6)](05-REST_API.md)
&emsp;&emsp;
[![](https://img.shields.io/badge/Next-➡️-caddd6?style=for-the-badge&labelColor=caddd6)](07-DEPENDENCY_INJECTION.md)

</div>

[![](https://img.shields.io/badge/Go_Back-🔙-d6cadd?style=for-the-badge&labelColor=d6cadd)](00-TABLE_CONTENT_README.md)

---
