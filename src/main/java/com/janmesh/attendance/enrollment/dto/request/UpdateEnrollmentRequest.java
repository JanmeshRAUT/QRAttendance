package com.janmesh.attendance.enrollment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEnrollmentRequest {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "Course ID is required")
    private UUID courseId;
}
