package com.janmesh.attendance.enrollment.mapper;

import com.janmesh.attendance.enrollment.dto.request.CreateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.request.UpdateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.response.EnrollmentResponse;
import com.janmesh.attendance.enrollment.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    Enrollment toEntity(CreateEnrollmentRequest request);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentResponse toResponse(Enrollment enrollment);

    void updateEnrollment(UpdateEnrollmentRequest request, @MappingTarget Enrollment enrollment);
}
