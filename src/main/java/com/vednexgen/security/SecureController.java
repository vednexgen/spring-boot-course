package com.vednexgen.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// for service is this class to be restricted enable the .anyRequest().authenticated() in security configs.
@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {
        return "Only admins can access this endpoint.";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user")
    public String userOrAdminAccess() {
        return "User or Admin can access this endpoint.";
    }
}