package com.vednexgen.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomServiceToggleController {

    public static boolean serviceFlag;

    @GetMapping("/toggleFlag")
    public String toggleFlag() {
        serviceFlag = !serviceFlag;
        IO.println(serviceFlag);
        return "OK";
    }
}
