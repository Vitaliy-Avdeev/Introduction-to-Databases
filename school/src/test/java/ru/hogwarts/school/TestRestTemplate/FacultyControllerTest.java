//package ru.hogwarts.school.TestRestTemplate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//import ru.hogwarts.school.model.Faculty;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class FacultyControllerTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Test
//    public void testGetFacultyInfo() {
//        ResponseEntity<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/2", Faculty.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Faculty faculty = response.getBody();
//        assertNotNull(faculty);
//        assertEquals(2L, faculty.getId());
//        assertEquals("string", faculty.getName());
//        assertEquals("string", faculty.getColor());
//    }
//
//    @Test
//    public void testGetFacultyByColor() {
//        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/color/red", Faculty[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Faculty[] faculties = response.getBody();
//        assertNotNull(faculties);
//        for (Faculty faculty : faculties) {
//            assertEquals("string", faculty.getColor());
//        }
//    }
//
//    @Test
//    public void testCreateFaculty() {
//        Faculty faculty = new Faculty(1L, "Engineering", "blue");
//        ResponseEntity<Faculty> response = restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Faculty created = response.getBody();
//        assertNotNull(created);
//        assertNotNull(created.getId());
//        assertEquals("Engineering", created.getName());
//        assertEquals("blue", created.getColor());
//    }
//
//    @Test
//    public void testEditFaculty() {
//        Faculty faculty = new Faculty(3L, "Science", "green");
//        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Faculty updated = response.getBody();
//        assertNotNull(updated);
//        assertEquals(3L, updated.getId());
//        assertEquals("Science", updated.getName());
//        assertEquals("green", updated.getColor());
//    }
//
//    @Test
//    public void testDeleteFaculty() {
//        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/faculty/1", HttpMethod.DELETE, null, Void.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        ResponseEntity<Faculty> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/1", Faculty.class);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, getResponse.getStatusCode());
//    }
//
//    @Test
//    public void testGetAllFaculties() {
//        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/all", Faculty[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Faculty[] faculties = response.getBody();
//        assertNotNull(faculties);
//        assertTrue(faculties.length > 0);
//    }
//
//    @Test
//    void testUpdateNonExistentFaculty_returnsNotFound() {
//        HttpHeaders headers = new HttpHeaders();
//
//        HttpEntity<Faculty> request = new HttpEntity<>(new Faculty(1L, "Hufflepuff", "Yellow"), headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                "/faculty", HttpMethod.PUT, request, String.class);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertTrue(response.getBody().contains("Faculty not found"));
//    }
//}
