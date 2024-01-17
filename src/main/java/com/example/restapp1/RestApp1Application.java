package com.example.restapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestApp1Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApp1Application.class, args);
    }

}
