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
//import ru.hogwarts.school.controller.FacultyController;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.service.FacultyService;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//public class FacultyControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private FacultyService facultyService;
//
//    @InjectMocks
//    private FacultyController facultyController;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(facultyController).build();
//    }
//
//    @Test
//    void testGetFacultyInfo() throws Exception {
//        Faculty faculty = new Faculty(1L, "Engineering", "blue");
//        when(facultyService.getFaculty(1L)).thenReturn(faculty);
//
//        mockMvc.perform(get("/faculty/id/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Engineering"))
//                .andExpect(jsonPath("$.color").value("blue"));
//    }
//
//    @Test
//    void testGetFacultyByColor() throws Exception {
//        List<Faculty> faculties = List.of(new Faculty(2L, "Math", "green"));
//        when(facultyService.getFacultyByColor("green")).thenReturn(faculties);
//
//        mockMvc.perform(get("/faculty/color/green"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name").value("Math"))
//                .andExpect(jsonPath("$[0].color").value("green"));
//    }
//
//    @Test
//    void testCreateFaculty() throws Exception {
//        Faculty faculty = new Faculty(3L, "History", "red");
//        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
//
//        mockMvc.perform(post("/faculty")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(faculty)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("History"))
//                .andExpect(jsonPath("$.color").value("red"));
//    }
//
//    @Test
//    void testEditFaculty() throws Exception {
//        Faculty faculty = new Faculty(4L, "Physics", "white");
//        when(facultyService.editeFaculty(any(Faculty.class))).thenReturn(faculty);
//
//        mockMvc.perform(put("/faculty")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(faculty)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Physics"))
//                .andExpect(jsonPath("$.color").value("white"));
//    }
//
//    @Test
//    void testDeleteFaculty() throws Exception {
//        doNothing().when(facultyService).deleteFaculty(5L);
//
//        mockMvc.perform(delete("/faculty/5"))
//                .andExpect(status().isOk());
//
//        verify(facultyService).deleteFaculty(5L);
//    }
//
//    @Test
//    void testGetAllFaculties() throws Exception {
//        List<Faculty> faculties = List.of(new Faculty(6L, "Biology", "yellow"));
//        when(facultyService.getAllFaculties()).thenReturn(faculties);
//
//        mockMvc.perform(get("/faculty/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name").value("Biology"))
//                .andExpect(jsonPath("$[0].color").value("yellow"));
//    }
//
//    @Test
//    void testGetFacultyByEmptyColor() throws Exception {
//        when(facultyService.getFacultyByColor("")).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/faculty/color/"))
//                .andExpect(status().isNotFound()); // так как путь /faculty/color/ не существует
//    }
//}
//
