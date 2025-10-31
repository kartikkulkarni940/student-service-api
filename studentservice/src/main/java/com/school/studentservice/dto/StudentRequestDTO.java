package com.school.studentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {

    private String name;

    private String grade;

    private String mobileNumber;

    private String schoolName;
}
