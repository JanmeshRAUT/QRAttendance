package com.janmesh.attendance.attendance.controller;

import com.janmesh.attendance.attendance.dto.request.CreateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.request.UpdateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.response.AttendanceResponse;
import com.janmesh.attendance.attendance.service.interfaces.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> createAttendance(
            @Valid @RequestBody CreateAttendanceRequest request) {

        AttendanceResponse response = attendanceService.createAttendance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(
            @PathVariable UUID id) {

        AttendanceResponse response = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AttendanceResponse>> getAllAttendances(
            Pageable pageable) {

        Page<AttendanceResponse> response = attendanceService.getAllAttendances(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAttendanceRequest request) {

        AttendanceResponse response = attendanceService.updateAttendance(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable UUID id) {

        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
