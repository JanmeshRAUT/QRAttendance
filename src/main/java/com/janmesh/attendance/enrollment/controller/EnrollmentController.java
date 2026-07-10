package com.janmesh.attendance.enrollment.controller;

import com.janmesh.attendance.enrollment.dto.request.CreateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.request.UpdateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.response.EnrollmentResponse;
import com.janmesh.attendance.enrollment.service.interfaces.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(
            @Valid @RequestBody CreateEnrollmentRequest request) {

        EnrollmentResponse response = enrollmentService.createEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(
            @PathVariable UUID id) {

        EnrollmentResponse response = enrollmentService.getEnrollmentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<EnrollmentResponse>> getAllEnrollments(
            Pageable pageable) {

        Page<EnrollmentResponse> response = enrollmentService.getAllEnrollments(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> updateEnrollment(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEnrollmentRequest request) {

        EnrollmentResponse response = enrollmentService.updateEnrollment(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable UUID id) {

        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
