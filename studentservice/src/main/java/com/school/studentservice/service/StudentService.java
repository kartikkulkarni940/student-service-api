package com.school.studentservice.service;


import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO request);
    StudentResponseDTO getStudentById(String studentId);
    List<StudentResponseDTO> getAllStudents();
}
