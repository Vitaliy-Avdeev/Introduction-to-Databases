package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public List<Student> findByAge(int age) {
        List<Student> byAge = studentRepository.findByAge(age);
        return byAge;
    }

    public List<Student> getStudentsByAgeBetween(int minAge, int maxAge) {
        List<Student> byAgeBetween = studentRepository.findByAgeBetween(minAge, maxAge);
        return byAgeBetween;
    }
}