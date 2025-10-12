package com.vednexgen.aop.service;

import org.springframework.stereotype.Service;

@Service("aopUserService")
public class UserService {

    public void addUser(String name) {
        IO.println("User added: " + name);
    }

    public String getUser() {
        IO.println("Fetching user details...");
        return "Atrangi";
    }

    public void throwError() {
        IO.println("This will throw exception");
        throw new RuntimeException("Something went wrong!");
    }
}