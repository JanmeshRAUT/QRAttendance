package com.janmesh.attendance.qrsession.repository;

import com.janmesh.attendance.qrsession.entity.QRSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QRSessionRepository extends JpaRepository<QRSession, UUID> {

    boolean existsByQrToken(String qrToken);

    Optional<QRSession> findByQrToken(String qrToken);
}
