package com.vednexgen.jpa.user.controller;

import com.vednexgen.jpa.user.entity.User;
import com.vednexgen.jpa.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{name}")
    public User getUser(@PathVariable String name) {
        User byName = userService.findByName(name);
        return byName;
    }
}