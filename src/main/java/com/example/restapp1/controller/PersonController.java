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

    @GetMapping("/student/{id}")
    public Student getSingleStudent(@PathVariable("id") int id) {

        return jdbcClient.sql("SELECT * FROM student where id =:id")
                .params(Map.of("id", id))
                .query(Student.class)
                .single();
    }

    @DeleteMapping("/student/{id}")
    public int deleteStudent(@PathVariable("id") int id) {
        return jdbcClient.sql("DELETE FROM student where id=:id")
                .params(Map.of("id", id))
                .update();
    }

    @PutMapping("/student")
    public int updateStudent(@RequestBody Student student) {
        System.out.println(" ):::  " + student);
        return jdbcClient.sql("UPDATE student set name=:name,department=:department where id=:id")
                .params(Map.of("id", student.id(), "name", student.name(), "department", student.department()))
                .update();
    }

    @PostMapping("/student")
    public int addStudent(@RequestBody Student student) {
        return jdbcClient.sql("Insert into student (name,department) values (:name,:department) ")
                .params(Map.of("name", student.name(), "department", student.department()))
                .update();
    }


}
