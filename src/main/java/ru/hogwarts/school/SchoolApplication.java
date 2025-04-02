package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@OpenAPIDefinition
public class SchoolApplication {

    public static void main(String[] args) {
        run(SchoolApplication.class, args);
    }

}
