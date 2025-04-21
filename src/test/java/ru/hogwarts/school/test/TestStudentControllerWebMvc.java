package ru.hogwarts.school.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TestStudentControllerWebMvc {
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void shouldReturnStudentById() throws Exception {
        Student student = new Student(1L, "Лаванда Браун", 10);
        when(studentService.findStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

        verify(studentService).findStudent(student.getId());
    }

    @Test
    public void shouldReturnStudentsByAge() throws Exception {
        List<Student> students = List.of(new Student(1L, "Милисента Булстроуд", 11), new Student(2L, "Грегори Гойл", 11));
        when(studentService.getStudentsByAge(11)).thenReturn(students);

        mockMvc.perform(get("/student/age/11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getStudentsByAge(11);
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        Student student = new Student(1L, "Лаванда Браун", 10);
        when(studentService.addStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student")
                        .content(new ObjectMapper().writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

        verify(studentService).addStudent(any(Student.class));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(1L);
    }

    @Test
    public void shouldReturnAllStudents() throws Exception {
        List<Student> students = List.of(new Student(1L, "Милисента Булстроуд", 11), new Student(2L, "Грегори Гойл", 11));
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getAllStudents();
    }

    @Test
    public void shouldReturnStudentsByAgeRange() throws Exception {
        List<Student> students = List.of(new Student(1L, "Милисента Булстроуд", 11), new Student(2L, "Грегори Гойл", 11));
        when(studentService.getStudentsByAgeBetween(8, 14)).thenReturn(students);

        mockMvc.perform(get("/student/ageBetween?min=8&max=14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(students.size()));

        verify(studentService).getStudentsByAgeBetween(8, 14);
    }
}