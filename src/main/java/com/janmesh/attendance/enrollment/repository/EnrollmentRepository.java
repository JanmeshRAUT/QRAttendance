package com.janmesh.attendance.enrollment.repository;

import com.janmesh.attendance.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    boolean existsByStudentIdAndCourseId(UUID studentId, UUID courseId);

}
