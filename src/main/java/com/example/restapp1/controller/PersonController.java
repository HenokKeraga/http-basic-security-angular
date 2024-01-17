package com.example.restapp1.controller;

import com.example.restapp1.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
class PersonController {

    List<Student> database = new ArrayList<>();

    @GetMapping("/student")
    public List<Student> hello() {
        database.add(new Student(1,"dfjaskldf","asjdflkasd"));
        return database;
    }


    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student) {
        database.add(student);
        return student;
    }


}
