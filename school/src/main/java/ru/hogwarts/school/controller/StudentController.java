package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repositiry.StudentRepository;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/ageBetween")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/sum_all_Students")
    public ResponseEntity<List<FullListOfStudents>> getSumStudents() {
        return ResponseEntity.ok(studentService.getSumStudents());
    }

    @GetMapping("/Average_Age_Of_Students")
    public ResponseEntity<List<AverageAgeOfStudents>> getAverageAgeOfStudents() {
        return ResponseEntity.ok(studentService.getAverageAgeOfStudents());
    }

    @GetMapping("/Last_Five_Students")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }

    @GetMapping("/student/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/namesStartingWithLetterA")
    public ResponseEntity<List<String>> getStudentsNamesStartingWithLetterA() {
        List<Student> students = studentRepository.findAll();

        List<String> namesStartingWithLetterA = students.stream()
                .filter(student -> student.getName().toUpperCase().startsWith("Г"))
                .<String>map(Student::getName)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        return ResponseEntity.ok(namesStartingWithLetterA);
    }
    @GetMapping("/theAverageAgeOfAllStudents")
    public ResponseEntity<Double> getTheAverageAgeOfAllStudents() {
        List<Student> students = studentRepository.findAll();

        double theAverageAgeOfAllStudents = students.stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
        return ResponseEntity.ok(theAverageAgeOfAllStudents);
    }
    @GetMapping("/students/print-parallel")
    public void getStudentsPrintParallel() {
        studentService.getStudentsPrintParallel();
    }
    @GetMapping("/students/print-synchronized")
    public void getStudentsPrintSynchronized() {
        studentService.getStudentsPrintSynchronized();

    }

}
