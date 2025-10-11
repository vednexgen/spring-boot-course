package com.vednexgen.aop;

import com.vednexgen.aop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AspectDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.addUser("Atrangi");
            userService.getUser();
            try {
                userService.throwError();
            } catch (Exception ignored) {}
        };
    }
}