package com.janmesh.attendance.attendance.dto.response;

import com.janmesh.attendance.common.enums.AttendanceStatus;
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
public class AttendanceResponse {

    private UUID id;
    private UUID studentId;
    private UUID qrSessionId;
    private AttendanceStatus attendanceStatus;
    private LocalDateTime markedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
