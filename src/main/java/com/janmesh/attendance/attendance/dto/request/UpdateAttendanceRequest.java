package com.janmesh.attendance.attendance.dto.request;

import com.janmesh.attendance.common.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAttendanceRequest {

    @NotNull(message = "Attendance status is required")
    private AttendanceStatus attendanceStatus;

    @NotNull(message = "Marked At time is required")
    private LocalDateTime markedAt;
}
