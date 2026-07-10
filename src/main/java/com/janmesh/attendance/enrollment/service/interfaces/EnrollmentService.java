package com.janmesh.attendance.enrollment.service.interfaces;

import com.janmesh.attendance.enrollment.dto.request.CreateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.request.UpdateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.response.EnrollmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(CreateEnrollmentRequest request);

    EnrollmentResponse getEnrollmentById(UUID id);

    Page<EnrollmentResponse> getAllEnrollments(Pageable pageable);

    EnrollmentResponse updateEnrollment(UUID id, UpdateEnrollmentRequest request);

    void deleteEnrollment(UUID id);

}
