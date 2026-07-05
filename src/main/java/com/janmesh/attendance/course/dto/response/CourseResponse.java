package com.janmesh.attendance.course.dto.response;

import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Department;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private UUID id;

    private String courseCode;

    private String courseName;

    private String courseDescription;

    private Integer credits;

    private Integer semester;

    private Department department;

    private AccountStatus accountStatus;

    private UUID facultyId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}