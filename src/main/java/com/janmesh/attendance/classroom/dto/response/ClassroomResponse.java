package com.janmesh.attendance.classroom.dto.response;

import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.RoomType;
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
public class ClassroomResponse {

    private UUID id;
    private String roomNumber;
    private String building;
    private Integer floor;
    private Integer capacity;
    private RoomType roomType;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
