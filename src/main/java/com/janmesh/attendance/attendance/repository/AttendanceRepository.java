package com.janmesh.attendance.attendance.repository;

import com.janmesh.attendance.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    boolean existsByStudentIdAndQrSessionId(UUID studentId, UUID qrSessionId);

}
