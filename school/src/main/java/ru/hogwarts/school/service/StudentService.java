package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;

import ru.hogwarts.school.model.*;

        import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.hogwarts.school.repositiry.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service

public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public Student addStudent(Student student) {
        logger.info("Работает метод addStudent для студента = {}", student);
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Работает метод getStudent с id = {}", id);
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Работает метод editeStudent для студента = {}", student);
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Вызван метод deleteStudent с id = {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Вызван метод getAllStudents");
        return studentRepository.findAll();
    }


    public Collection<Student> getStudentsByAge(int age) {
        logger.info("Работает метод getStudentsByAge с age = {}", age);
        return studentRepository.findAll().stream().filter(s -> s.getAge() == age).toList();
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> getStudentsByAgeBetween(int minAge, int maxAge) {
        logger.info("Работает метод getStudentsByAgeBetween с min = {} и max = {}", minAge, maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<FullListOfStudents> getSumStudents() {
        logger.info("Работает метод getSumStudents");
        return studentRepository.getSumStudents();
    }

    public List<AverageAgeOfStudents> getAverageAgeOfStudents() {
        logger.info("Работает метод getAverageAgeOfStudents");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Работает метод getLastFiveStudents");
        return studentRepository.getLastFiveStudents();
    }

    public List<Student> getStudentByName(String name) {
        logger.info("Работает метод getStudentByName с именеем = {}", name);
        return studentRepository.getStudentsByName(name);
    }

    public void getStudentsPrintParallel() {
        logger.info("Был вызван метод для получения имен всех студентов в параллельном режиме..");

        List<Student> students = studentRepository.findAll();

        System.out.println("Первый студент -" + students.get(0).getName());
        System.out.println("Второй студент -" + students.get(1).getName());

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Третий студент -" + students.get(2).getName());
            System.out.println("Четвертый студент -" + students.get(3).getName());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Пятый студент -" + students.get(4).getName());
            System.out.println("Шестой студент -" + students.get(5).getName());
        }).start();

    }

    private synchronized String printNameOfStudent(Student student) {
        return student.getName();
    }

    public void getStudentsPrintSynchronized() {
        logger.info("Был вызван метод для получения имен всех студентов в синхронном режиме..");

        List<Student> students = studentRepository.findAll();

        System.out.println("Первый студент -" + printNameOfStudent(students.get(0)));
        System.out.println("Второй студент -" + printNameOfStudent(students.get(1)));

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Третий студент -" + printNameOfStudent(students.get(2)));
            System.out.println("Четвертый студент -" + printNameOfStudent(students.get(3)));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Пятый студент -" + printNameOfStudent(students.get(4)));
            System.out.println("Шестой студент -" + printNameOfStudent(students.get(5)));
        }).start();
    }
}

