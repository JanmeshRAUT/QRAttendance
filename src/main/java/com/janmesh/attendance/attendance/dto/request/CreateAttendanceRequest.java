package com.janmesh.attendance.attendance.dto.request;

import com.janmesh.attendance.common.enums.AttendanceStatus;
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
public class CreateAttendanceRequest {

    @NotNull(message = "Student ID is required")
    private UUID studentId;

    @NotNull(message = "QR Session ID is required")
    private UUID qrSessionId;

    @NotNull(message = "Attendance status is required")
    private AttendanceStatus attendanceStatus;

    @NotNull(message = "Marked At time is required")
    private LocalDateTime markedAt;
}
