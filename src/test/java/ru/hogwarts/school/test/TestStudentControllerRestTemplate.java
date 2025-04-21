package ru.hogwarts.school.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestStudentControllerRestTemplate {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetStudent() throws Exception {
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/id/4", Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student student = response.getBody();
        assertNotNull(student);
        assertEquals("Невилл Долгопупс", student.getName());
        assertEquals(10, student.getAge());
    }

    @Test
    public void testGetStudentByAge() throws Exception {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/age/12", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student[] students = response.getBody();
        assertNotNull(students);
        for (Student student : students) {
            assertEquals(12, student.getAge());
        }
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student(11L, "Невилл Долгопупс", 14);
        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:" + port + "/student", student, Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student createdStudent = response.getBody();
        assertNotNull(createdStudent);
        assertEquals("Невилл Долгопупс", createdStudent.getName());
        assertEquals(14, createdStudent.getAge());

    }

    @Test
    public void testEditStudent() {
        Student student = new Student(12L, "Винсент Крэбб", 12);
        ResponseEntity<Student> response = restTemplate.exchange(
                "http://localhost:" + port + "/student", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student updatedStudent = response.getBody();
        assertNotNull(updatedStudent);
        assertEquals("Винсент Крэбб", updatedStudent.getName());
        assertEquals(12, updatedStudent.getAge());
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/all", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student[] students = response.getBody();
        assertNotNull(students);
        assertTrue(students.length > 0);
    }

    @Test
    public void testDeleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/student/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<Student> checkResponse = restTemplate.getForEntity("http://localhost:" + port + "/student/id/1", Student.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, checkResponse.getStatusCode());
    }

    @Test
    public void testGetStudentsByAgeBetween() {

        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/ageBetween?min=8&max=14", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Student[] students = response.getBody();
        assertNotNull(students);
        for (Student student : students) {
            assertTrue(student.getAge() >= 8 && student.getAge() <= 14);
        }
    }

    @Test
    public void testGetNonExistingStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/id/9999999", Student.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}