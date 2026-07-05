package com.janmesh.attendance.student.dto.request;

import com.janmesh.attendance.common.enums.AcademicYear;
import com.janmesh.attendance.common.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotBlank(message = "Roll number is required")
    @Size(max = 50)
    private String rollNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Size(max = 15)
    private String phoneNumber;

    @NotNull(message = "Academic year is required")
    private AcademicYear academicYear;

    @NotBlank(message = "Division is required")
    @Size(max = 10)
    private String division;

    @NotNull(message = "Admission year is required")
    private Integer admissionYear;

    @NotNull(message = "User Id is required")
    private UUID userId;
}