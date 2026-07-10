package com.janmesh.attendance.qrsession.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateQRSessionRequest {

    @NotNull(message = "Course ID is required")
    private UUID courseId;

    @NotNull(message = "Faculty ID is required")
    private UUID facultyId;

    private UUID classroomId; // nullable

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotBlank(message = "QR token is required")
    private String qrToken;
    
    @NotNull(message = "Active status is required")
    private Boolean active;
}
