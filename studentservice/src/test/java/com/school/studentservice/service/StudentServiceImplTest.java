package com.school.studentservice.service;

import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;
import com.school.studentservice.entity.Student;
import com.school.studentservice.exception.ResourceNotFoundException;
import com.school.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl service;

    private StudentRequestDTO request;
    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = StudentRequestDTO.builder()
                .name("Ravi Kumar")
                .grade("10")
                .mobileNumber("9999999999")
                .schoolName("Delhi Public School")
                .build();

        student = Student.builder()
                .id(1L)
                .studentId("S-12345678")
                .name("Ravi Kumar")
                .grade("10")
                .mobileNumber("9999999999")
                .schoolName("Delhi Public School")
                .build();
    }

    @Test
    void testCreateStudent_Success() {
        when(repository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO response = service.createStudent(request);

        assertNotNull(response);
        assertEquals("Ravi Kumar", response.getName());
        assertTrue(response.getStudentId().startsWith("S-"));
        verify(repository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetStudentById_Success() {
        when(repository.findByStudentId("S-12345678")).thenReturn(Optional.of(student));

        StudentResponseDTO result = service.getStudentById("S-12345678");

        assertNotNull(result);
        assertEquals("Ravi Kumar", result.getName());
        verify(repository).findByStudentId("S-12345678");
    }

    @Test
    void testGetStudentById_NotFound() {
        when(repository.findByStudentId("S-999999")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getStudentById("S-999999"));
        verify(repository).findByStudentId("S-999999");
    }

    @Test
    void testGetAll_Students_Success() {
        when(repository.findAll()).thenReturn(List.of(student));

        List<StudentResponseDTO> list = service.getAllStudents();

        assertEquals(1, list.size());
        assertEquals("Ravi Kumar", list.get(0).getName());
        verify(repository).findAll();
    }
}
