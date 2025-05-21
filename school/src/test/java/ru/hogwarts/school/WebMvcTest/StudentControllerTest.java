//package ru.hogwarts.school.WebMvcTest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import ru.hogwarts.school.controller.StudentController;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.service.StudentService;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(MockitoExtension.class)
//public class StudentControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private StudentService studentService;
//
//    @InjectMocks
//    private StudentController studentController;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
//    }
//
//    @Test
//    public void shouldReturnStudentById() throws Exception {
//        Student student = new Student(1L, "John Doe", 20);
//        when(studentService.getStudent(1L)).thenReturn(student);
//
//        mockMvc.perform(get("/student/id/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(student.getId()))
//                .andExpect(jsonPath("$.name").value(student.getName()))
//                .andExpect(jsonPath("$.age").value(student.getAge()));
//
//        verify(studentService).getStudent(student.getId());
//    }
//
//    @Test
//    public void shouldReturnStudentsByAge() throws Exception {
//        List<Student> students = List.of(new Student(1L, "Alice", 20), new Student(2L, "Bob", 20));
//        when(studentService.getStudentsByAge(20)).thenReturn(students);
//
//        mockMvc.perform(get("/student/age/20"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(students.size()));
//
//        verify(studentService).getStudentsByAge(20);
//    }
//
//    @Test
//    public void shouldCreateStudent() throws Exception {
//        Student student = new Student(1L, "John Doe", 20);
//        when(studentService.createStudent(any(Student.class))).thenReturn(student);
//
//        mockMvc.perform(post("/student")
//                        .content(new ObjectMapper().writeValueAsString(student))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(student.getId()))
//                .andExpect(jsonPath("$.name").value(student.getName()))
//                .andExpect(jsonPath("$.age").value(student.getAge()));
//
//        verify(studentService).createStudent(any(Student.class));
//    }
//
//    @Test
//    public void shouldEditStudent() throws Exception {
//        Student student = new Student(1L, "John Doe", 21);
//        when(studentService.editeStudent(any(Student.class))).thenReturn(student);
//
//        mockMvc.perform(put("/student")
//                        .content(new ObjectMapper().writeValueAsString(student))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.age").value(student.getAge()));
//
//        verify(studentService).editeStudent(any(Student.class));
//    }
//
//    @Test
//    public void shouldDeleteStudent() throws Exception {
//        doNothing().when(studentService).deleteStudent(1L);
//
//        mockMvc.perform(delete("/student/1"))
//                .andExpect(status().isOk());
//
//        verify(studentService).deleteStudent(1L);
//    }
//
//    @Test
//    public void shouldReturnAllStudents() throws Exception {
//        List<Student> students = List.of(new Student(1L, "Alice", 20), new Student(2L, "Bob", 21));
//        when(studentService.getAllStudents()).thenReturn(students);
//
//        mockMvc.perform(get("/student/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(students.size()));
//
//        verify(studentService).getAllStudents();
//    }
//
//    @Test
//    public void shouldReturnStudentsByAgeRange() throws Exception {
//        List<Student> students = List.of(new Student(1L, "Alice", 20), new Student(2L, "Bob", 21));
//        when(studentService.getStudentsByAgeBetween(19, 22)).thenReturn(students);
//
//        mockMvc.perform(get("/student/ageBetween?min=19&max=22"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(students.size()));
//
//        verify(studentService).getStudentsByAgeBetween(19, 22);
//    }
//
//    @Test
//    public void shouldReturnBadRequestWhenCreatingStudentWithInvalidData() throws Exception {
//        Student invalidStudent = new Student(null, "", -1);
//
//        mockMvc.perform(post("/student")
//                        .content(new ObjectMapper().writeValueAsString(invalidStudent))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//
//        verify(studentService, times(0)).createStudent(any(Student.class));  // Сервис не должен вызываться
//    }
//}
