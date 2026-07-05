package com.janmesh.attendance.course.dto.request;

import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Department;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {

    @NotBlank(message = "Course name is required")
    @Size(max = 100)
    private String courseName;

    @Size(max = 255)
    private String courseDescription;

    @NotNull(message = "Credits are required")
    @Min(1)
    @Max(10)
    private Integer credits;

    @NotNull(message = "Semester is required")
    @Min(1)
    @Max(8)
    private Integer semester;

    @NotNull(message = "Department is required")
    private Department department;

    @NotNull(message = "Account Status is required")
    private AccountStatus accountStatus;

}