package com.vednexgen.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureEmployeeController {

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public information.";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "This is user profile page.";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin, to the dashboard.";
    }
}