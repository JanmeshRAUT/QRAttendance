package com.janmesh.attendance.attendance.mapper;

import com.janmesh.attendance.attendance.dto.request.CreateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.request.UpdateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.response.AttendanceResponse;
import com.janmesh.attendance.attendance.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    Attendance toEntity(CreateAttendanceRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "qrSessionId", source = "qrSession.id")
    AttendanceResponse toResponse(Attendance attendance);

    void updateAttendance(UpdateAttendanceRequest request, @MappingTarget Attendance attendance);
}
