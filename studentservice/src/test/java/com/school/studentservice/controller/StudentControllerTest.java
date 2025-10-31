package com.school.studentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;
import com.school.studentservice.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentResponseDTO response;

    @BeforeEach
    void setUp() {
        response = StudentResponseDTO.builder()
                .id(1L)
                .studentId("S-12345678")
                .name("Ravi Kumar")
                .grade("10")
                .mobileNumber("9999999999")
                .schoolName("Delhi Public School")
                .build();
    }

    @Test
    void createStudent_shouldReturn201AndBody() throws Exception {
        StudentRequestDTO request = StudentRequestDTO.builder()
                .name("Ravi Kumar")
                .grade("10")
                .mobileNumber("9999999999")
                .schoolName("Delhi Public School")
                .build();

        when(studentService.createStudent(any(StudentRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId").value("S-12345678"))
                .andExpect(jsonPath("$.name").value("Ravi Kumar"));
    }

    @Test
    void getByStudentId_shouldReturn200AndBody() throws Exception {
        when(studentService.getStudentById("S-12345678")).thenReturn(response);

        mockMvc.perform(get("/api/students/S-12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ravi Kumar"))
                .andExpect(jsonPath("$.grade").value("10"));
    }

    @Test
    void listAll_shouldReturn200AndList() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentId").value("S-12345678"))
                .andExpect(jsonPath("$[0].name").value("Ravi Kumar"));
    }
}
