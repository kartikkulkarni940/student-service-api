package com.school.studentservice.service;

import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;
import com.school.studentservice.entity.Student;
import com.school.studentservice.exception.ResourceNotFoundException;
import com.school.studentservice.repository.StudentRepository;
import com.school.studentservice.util.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    /**
     * Creates a new student record.
     *
     * @param request DTO containing student details.
     * @return created student response.
     */
    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO request) {
        log.info("[createStudent] Creating student: name={}, grade={}, school={}",
                request.getName(), request.getGrade(), request.getSchoolName());

        Student entity = StudentMapper.toEntity(request);
        entity.setStudentId("S-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        Student saved = repository.save(entity);
        log.info("[createStudent] Student created successfully with studentId={}", saved.getStudentId());

        return StudentMapper.toDto(saved);
    }

    /**
     * Fetch a single student by business ID.
     *
     * @param studentId business ID.
     * @return student response DTO.
     */
    @Override
    public StudentResponseDTO getStudentById(String studentId) {
        log.info("[getStudentById] Fetching student by ID={}", studentId);

        Student student = repository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + studentId));

        log.info("[getStudentById] Found student: name={}, grade={}, school={}",
                student.getName(), student.getGrade(), student.getSchoolName());

        return StudentMapper.toDto(student);
    }

    /**
     * Fetch all students from database.
     *
     * @return list of student DTOs.
     */
    @Override
    public List<StudentResponseDTO> getAllStudents() {
        log.info("[getAllStudents] Fetching all students...");

        List<StudentResponseDTO> students = repository.findAll()
                .stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());

        log.info("[getAllStudents] Total students found: {}", students.size());
        return students;
    }
}
