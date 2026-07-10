package com.janmesh.attendance.qrsession.controller;

import com.janmesh.attendance.qrsession.dto.request.CreateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.request.UpdateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.response.QRSessionResponse;
import com.janmesh.attendance.qrsession.service.interfaces.QRSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/qrsessions")
@RequiredArgsConstructor
public class QRSessionController {

    private final QRSessionService qrSessionService;

    @PostMapping
    public ResponseEntity<QRSessionResponse> createQRSession(
            @Valid @RequestBody CreateQRSessionRequest request) {

        QRSessionResponse response = qrSessionService.createQRSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QRSessionResponse> getQRSessionById(
            @PathVariable UUID id) {

        QRSessionResponse response = qrSessionService.getQRSessionById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<QRSessionResponse>> getAllQRSessions(
            Pageable pageable) {

        Page<QRSessionResponse> response = qrSessionService.getAllQRSessions(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QRSessionResponse> updateQRSession(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateQRSessionRequest request) {

        QRSessionResponse response = qrSessionService.updateQRSession(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQRSession(
            @PathVariable UUID id) {

        qrSessionService.deleteQRSession(id);
        return ResponseEntity.noContent().build();
    }
}
