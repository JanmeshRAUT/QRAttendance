package com.janmesh.attendance.qrsession.service.interfaces;

import com.janmesh.attendance.qrsession.dto.request.CreateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.request.UpdateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.response.QRSessionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface QRSessionService {

    QRSessionResponse createQRSession(CreateQRSessionRequest request);

    QRSessionResponse getQRSessionById(UUID id);

    Page<QRSessionResponse> getAllQRSessions(Pageable pageable);

    QRSessionResponse updateQRSession(UUID id, UpdateQRSessionRequest request);

    void deleteQRSession(UUID id);

}
