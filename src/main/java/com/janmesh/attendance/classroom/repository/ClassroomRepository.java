package com.janmesh.attendance.classroom.repository;

import com.janmesh.attendance.classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    boolean existsByRoomNumber(String roomNumber);

    Optional<Classroom> findByRoomNumber(String roomNumber);

}
