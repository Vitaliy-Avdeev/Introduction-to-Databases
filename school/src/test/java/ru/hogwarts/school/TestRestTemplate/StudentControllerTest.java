//package ru.hogwarts.school.TestRestTemplate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import ru.hogwarts.school.model.Student;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class StudentControllerTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Test
//    public void testGetStudentInfo() {
//        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/id/2", Student.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student student = response.getBody();
//        assertNotNull(student);
//        assertEquals("string", student.getName());
//        assertEquals(0, student.getAge());
//    }
//
//    @Test
//    public void testGetStudentsByAge() {
//        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/age/20", Student[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student[] students = response.getBody();
//        assertNotNull(students);
//        for (Student student : students) {
//            assertEquals(20, student.getAge());
//        }
//    }
//
//    @Test
//    public void testCreateStudent() {
//        Student student = new Student(1L, "try test", 20);
//        ResponseEntity<Student> response = restTemplate.postForEntity("http://localhost:" + port + "/student", student, Student.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student createdStudent = response.getBody();
//        assertNotNull(createdStudent);
//        assertEquals("try test", createdStudent.getName());
//        assertEquals(20, createdStudent.getAge());
//    }
//
//    @Test
//    public void testEditStudent() {
//        Student student = new Student(3L, "John Doe", 21);
//        ResponseEntity<Student> response = restTemplate.exchange(
//                "http://localhost:" + port + "/student", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student updatedStudent = response.getBody();
//        assertNotNull(updatedStudent);
//        assertEquals("John Doe", updatedStudent.getName());
//        assertEquals(21, updatedStudent.getAge());
//    }
//
//    @Test
//    public void testDeleteStudent() {
//        ResponseEntity<Void> response = restTemplate.exchange(
//                "http://localhost:" + port + "/student/5", HttpMethod.DELETE, null, Void.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        ResponseEntity<Student> checkResponse = restTemplate.getForEntity("http://localhost:" + port + "/student/id/1", Student.class);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, checkResponse.getStatusCode());
//    }
//
//    @Test
//    public void testGetAllStudents() {
//        ResponseEntity<Student[]> response = restTemplate.getForEntity("http://localhost:" + port + "/student/all", Student[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student[] students = response.getBody();
//        assertNotNull(students);
//        assertTrue(students.length > 0);
//    }
//
//    @Test
//    public void testGetStudentsByAgeBetween() {
//        ResponseEntity<Student[]> response = restTemplate.getForEntity(
//                "http://localhost:" + port + "/student/ageBetween?min=18&max=25", Student[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        Student[] students = response.getBody();
//        assertNotNull(students);
//        for (Student student : students) {
//            assertTrue(student.getAge() >= 18 && student.getAge() <= 25);
//        }
//    }
//
//    @Test
//    public void testGetNonExistingStudent() {
//        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/id/9999999", Student.class);
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//}
