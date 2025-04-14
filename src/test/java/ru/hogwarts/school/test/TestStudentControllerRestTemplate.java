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

import ru.hogwarts.school.controller.StudentController;

import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestStudentControllerRestTemplate {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController StudentController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentController studentController;

    @Test
    public void contextLoadsStudent() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAge() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/age/12", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Гарри Поттер");
        student.setAge(14);
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/all", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditStudent() {
        Student student = new Student(2L, "Невилл Долгопупс", 12);
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteStudent() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/student/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}