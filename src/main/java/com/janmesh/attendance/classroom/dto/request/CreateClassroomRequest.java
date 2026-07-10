package com.janmesh.attendance.classroom.dto.request;

import com.janmesh.attendance.common.enums.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassroomRequest {

    @NotBlank(message = "Room number is required")
    @Size(max = 20)
    private String roomNumber;

    @NotBlank(message = "Building is required")
    @Size(max = 100)
    private String building;

    @NotNull(message = "Floor is required")
    private Integer floor;

    @NotNull(message = "Capacity is required")
    @Min(1)
    private Integer capacity;

    @NotNull(message = "Room type is required")
    private RoomType roomType;
}
