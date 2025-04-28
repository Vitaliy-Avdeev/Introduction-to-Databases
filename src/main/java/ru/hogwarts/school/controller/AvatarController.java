package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class AvatarController {
    private final StudentService studentService;

    public AvatarController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/avatar")
    public ResponseEntity<List<Avatar>> getAllStudent(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> students = studentService.getAllStudent(pageSize, pageNumber);
        return ResponseEntity.ok(students);
    }
}