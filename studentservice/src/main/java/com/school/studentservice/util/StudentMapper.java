package com.school.studentservice.util;

import com.school.studentservice.dto.StudentRequestDTO;
import com.school.studentservice.dto.StudentResponseDTO;
import com.school.studentservice.entity.Student;

public final class StudentMapper {

    private StudentMapper() {}

    public static Student toEntity(StudentRequestDTO dto) {
        if (dto == null) return null;
        return Student.builder()
                .name(dto.getName())
                .grade(dto.getGrade())
                .mobileNumber(dto.getMobileNumber())
                .schoolName(dto.getSchoolName())
                .build();
    }

    public static StudentResponseDTO toDto(Student entity) {
        if (entity == null) return null;
        return StudentResponseDTO.builder()
                .id(entity.getId())
                .studentId(entity.getStudentId())
                .name(entity.getName())
                .grade(entity.getGrade())
                .mobileNumber(entity.getMobileNumber())
                .schoolName(entity.getSchoolName())
                .build();
    }
}
