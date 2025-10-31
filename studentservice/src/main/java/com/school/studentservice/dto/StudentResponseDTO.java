package com.school.studentservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {
    private Long id;
    private String studentId;   // business id
    private String name;
    private String grade;
    private String mobileNumber;
    private String schoolName;
}
