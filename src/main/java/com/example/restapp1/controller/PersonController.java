package com.example.restapp1.controller;

import com.example.restapp1.model.Student;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
class PersonController {

    private final JdbcClient jdbcClient;


    PersonController(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @GetMapping("/students")
    public List<Student> hello() {

        return jdbcClient
                .sql("Select * from student").query(Student.class)
                .list();
    }

    @GetMapping("/students/{id}")
    public Student getSingleStudent(@PathVariable("id") int id) {

        return jdbcClient.sql("SELECT * FROM student where id =:id")
                .params(Map.of("id", id))
                .query(Student.class)
                .single();
    }

    @DeleteMapping("/students/{id}")
    public int deleteStudent(@PathVariable("id") int id) {
        return jdbcClient.sql("DELETE FROM student where id=:id")
                .params(Map.of("id", id))
                .update();
    }

    @PutMapping("/student")
    public int updateStudent(@RequestBody Student student) {
        System.out.println(" ):::  " + student);
        return jdbcClient.sql("UPDATE student set name=:name,department=:department,age=:age where id=:id")
                .params(Map.of("id", student.id(), "name", student.name(), "department", student.department()))
                .update();
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student) {
        var n = jdbcClient.sql("Insert into student (name,department,age) values (:name,:department,:age) ")
                .params(Map.of("name", student.name(), "department", student.department(), "age", student.age()))
                .update();
        if (n == 1) {
            return student;
        }
        throw new RuntimeException("Student is not created");

    }

    @PatchMapping("/students/{id}")
    public int updateAge(@PathVariable("id") Integer id, @RequestBody Student student) {

        return this.jdbcClient.sql("UPDATE student set age=:age where id=:id")
                .param("id", id)
                .param("age", student.age())
                .update();
    }


}
