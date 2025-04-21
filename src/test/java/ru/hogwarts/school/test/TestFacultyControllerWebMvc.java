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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TestFacultyControllerWebMvc {
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(facultyController).build();
    }

    @Test
    void testGetFacultyInfo() throws Exception {
        Faculty faculty = new Faculty(1L, "Гриффиндор", "красный");
        when(facultyService.getFaculty(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Гриффиндор"))
                .andExpect(jsonPath("$.color").value("красный"));
    }

    @Test
    void testGetFacultyByColor() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(1L, "Гриффиндор", "красный"));
        when(facultyService.findByColor("красный")).thenReturn(faculties);

        mockMvc.perform(get("/faculty/color/красный"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Гриффиндор"))
                .andExpect(jsonPath("$[0].color").value("красный"));
    }

    @Test
    void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty(3L, "Когтевран", "синий");
        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Когтевран"))
                .andExpect(jsonPath("$.color").value("синий"));
    }

    @Test
    void testEditFaculty() throws Exception {
        Faculty faculty = new Faculty(4L, "Пуффендуй", "жёлтый");
        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Пуффендуй"))
                .andExpect(jsonPath("$.color").value("жёлтый"));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        doNothing().when(facultyService).deleteFaculty(4L);

        mockMvc.perform(delete("/faculty/4"))
                .andExpect(status().isOk());

        verify(facultyService).deleteFaculty(4L);
    }

    @Test
    void testGetAllFaculties() throws Exception {
        List<Faculty> faculties = List.of(new Faculty(3L, "Когтевран", "синий"));
        when(facultyService.getAllFaculties()).thenReturn(faculties);

        mockMvc.perform(get("/faculty/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Когтевран"))
                .andExpect(jsonPath("$[0].color").value("синий"));
    }

}