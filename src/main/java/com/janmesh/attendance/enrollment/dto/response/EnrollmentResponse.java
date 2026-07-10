package com.janmesh.attendance.enrollment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {

    private UUID id;
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
