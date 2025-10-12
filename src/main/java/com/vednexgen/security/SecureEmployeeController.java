package com.vednexgen.security;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureEmployeeController {

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public information.";
    }

    @PostMapping("/public/info")
    public String postPublicInfo() {
        return "Posting public information.";
    }

    @GetMapping("/user/profile")
    @SecurityRequirement(name = "basicAuth")
    public String userProfile() {
        return "This is user profile page.";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin, to the dashboard.";
    }
}