package com.vednexgen.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customServcie")
public class CustomServiceHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean serviceRunning = checkCustomService();
        if (serviceRunning) {
            return Health.up().withDetail("customService", "Running Smoothly").build();
        }
        return Health.down().withDetail("customService", "Not Responding").build();
    }

    private boolean checkCustomService() {
        // Simulate health check
        return CustomServiceToggleController.serviceFlag;
    }
}