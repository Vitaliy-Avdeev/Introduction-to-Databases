package ru.hogwarts.school.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import ru.hogwarts.school.model.Faculty;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestFacultyControllerRestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testGetFaculty() throws Exception {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/2", Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty faculty = response.getBody();
        assertNotNull(faculty);
        assertEquals(2L, faculty.getId());
        assertEquals("Слизерин", faculty.getName());
        assertEquals("зелёный", faculty.getColor());
    }

    @Test
    public void testGetFacultyByColor() {
        ResponseEntity<Faculty[]> response;
        response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/color/красный", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty[] faculties = response.getBody();
        assertNotNull(faculties);
        for (Faculty faculty : faculties) {
            assertEquals("string", faculty.getColor());
        }
    }
    @Test
    public void testCreateFaculty() {
        Faculty faculty = new Faculty(1L, "Пуффендуй", "жёлтый");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty created = response.getBody();
        assertNotNull(created);
        assertEquals("Пуффендуй", created.getName());
        assertEquals("жёлтый", created.getColor());
    }
    @Test
    public void testEditFaculty() {
        Faculty faculty = new Faculty(2L, "Слизерин", "зелёный");
        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty updated = response.getBody();
        assertNotNull(updated);
        assertEquals(2L, updated.getId());
        assertEquals("Слизерин", updated.getName());
        assertEquals("зелёный", updated.getColor());
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty/all", Faculty[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Faculty[] faculties = response.getBody();
        assertNotNull(faculties);
        assertTrue(faculties.length > 0);
    }

    @Test
    public void testDeleteFaculty() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/faculty/1", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<Faculty> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/faculty/id/1", Faculty.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, getResponse.getStatusCode());
    }

    }

