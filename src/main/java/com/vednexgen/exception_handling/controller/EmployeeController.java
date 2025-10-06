package com.vednexgen.exception_handling.controller;

import com.vednexgen.exception_handling.model.EmployeeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public EmployeeDTO getUser(@PathVariable int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Invalid ID");
        } else if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }
        return new EmployeeDTO("John", "john@vednexgen.com", 23);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidId(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody EmployeeDTO user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errors", errors);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok("User created successfully");
    }

}