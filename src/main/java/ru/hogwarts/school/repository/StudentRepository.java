package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.AverageAgeOfStudents;
import ru.hogwarts.school.model.FullListOfStudents;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(name) as sumStudents FROM student", nativeQuery = true)
    List<FullListOfStudents> getSumStudents();

    @Query(value = "SELECT MIN(age) as minAge, MAX(age) as maxAge, AVG(age) as averageAge FROM student WHERE name != 'null'", nativeQuery = true)
    List<AverageAgeOfStudents> getAverageAgeOfStudents();

    @Query(value = "SELECT * FROM student WHERE name != 'null' ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

}
