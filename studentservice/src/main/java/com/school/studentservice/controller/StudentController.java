package com.school.studentservice.controller;

import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;
import com.school.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student Service", description = "Handles student registration and retrieval")
public class StudentController {

    private final StudentService studentService;

    /**
     * Create a new student record.
     *
     * @param request student details
     * @return created student response
     */
    @Operation(summary = "Create student", description = "Creates a new student record in the database.")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO request) {
        log.info("Create student request: name={}", request.getName());
        StudentResponseDTO created = studentService.createStudent(request);
        return ResponseEntity.created(URI.create("/api/students/" + created.getStudentId())).body(created);
    }

    /**
     * Get student by unique studentId.
     *
     * @param studentId unique student ID
     * @return student details
     */
    @Operation(summary = "Get student by ID", description = "Fetches student details using studentId.")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable String studentId) {
        log.info("Fetch student by ID: {}", studentId);
        StudentResponseDTO dto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(dto);
    }

    /**
     * Get all students.
     *
     * @return list of all students
     */
    @Operation(summary = "List all students", description = "Returns all registered students in the system.")
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> listAllStudents() {
        log.info("List all students");
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
