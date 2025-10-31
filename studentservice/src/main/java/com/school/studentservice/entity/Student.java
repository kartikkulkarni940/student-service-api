package com.school.studentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  identifier exposed to other services
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    @NotBlank(message = "Student ID cannot be blank")
    private String studentId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Column(length = 10)
    @Pattern(regexp = "^[A-Za-z0-9\\-]+$", message = "Grade must contain only letters, numbers, or hyphens")
    private String grade;

    @Column(length = 15)
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must be between 10 and 15 digits")
    private String mobileNumber;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "School name is required")
    @Size(max = 150, message = "School name cannot exceed 150 characters")
    private String schoolName;


    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(nullable = false)
    private java.time.LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = this.createdAt;
        if (this.studentId == null || this.studentId.isEmpty()) {
            this.studentId = "S-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
}
