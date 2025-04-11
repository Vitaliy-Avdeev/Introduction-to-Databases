package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoadsFaculty() throws Exception {
        assertThat(facultyController).isNotNull();
    }

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
    public void testGetFaculty() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Гарри Поттер");

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Гриффиндор");

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Когтевран");
        Faculty createFaculty = this.restTemplate.postForObject("/faculty", faculty, Faculty.class);
        this.restTemplate.delete("/faculty/" + createFaculty.getId());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Лаванда Браун");
        Student createStudent = this.restTemplate.postForObject("/student", student, Student.class);
        this.restTemplate.delete("/student/" + createStudent.getId());
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        Student createStudent = this.restTemplate.postForObject("/student", student, Student.class);
        Student putStudent = new Student();
        this.restTemplate.put("/student/" + createStudent.getId(), putStudent);
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty();
        Faculty createFaculty = this.restTemplate.postForObject("/faculty", faculty, Faculty.class);
        Faculty putFaculty = new Faculty();
        this.restTemplate.put("/faculty/" + createFaculty.getId(), putFaculty);
    }
}
