package com.janmesh.attendance.attendance.service.interfaces;

import com.janmesh.attendance.attendance.dto.request.CreateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.request.UpdateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.response.AttendanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AttendanceService {

    AttendanceResponse createAttendance(CreateAttendanceRequest request);

    AttendanceResponse getAttendanceById(UUID id);

    Page<AttendanceResponse> getAllAttendances(Pageable pageable);

    AttendanceResponse updateAttendance(UUID id, UpdateAttendanceRequest request);

    void deleteAttendance(UUID id);

}
