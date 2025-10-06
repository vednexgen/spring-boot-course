package com.vednexgen.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String email;

}