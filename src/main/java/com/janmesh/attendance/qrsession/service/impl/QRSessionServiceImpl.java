package com.janmesh.attendance.qrsession.service.impl;

import com.janmesh.attendance.classroom.entity.Classroom;
import com.janmesh.attendance.classroom.repository.ClassroomRepository;
import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import com.janmesh.attendance.course.entity.Course;
import com.janmesh.attendance.course.repository.CourseRepository;
import com.janmesh.attendance.faculty.entity.Faculty;
import com.janmesh.attendance.faculty.repository.FacultyRepository;
import com.janmesh.attendance.qrsession.dto.request.CreateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.request.UpdateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.response.QRSessionResponse;
import com.janmesh.attendance.qrsession.entity.QRSession;
import com.janmesh.attendance.qrsession.mapper.QRSessionMapper;
import com.janmesh.attendance.qrsession.repository.QRSessionRepository;
import com.janmesh.attendance.qrsession.service.interfaces.QRSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class QRSessionServiceImpl implements QRSessionService {

    private final QRSessionRepository qrSessionRepository;
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final ClassroomRepository classroomRepository;
    private final QRSessionMapper qrSessionMapper;

    @Override
    public QRSessionResponse createQRSession(CreateQRSessionRequest request) {

        if (qrSessionRepository.existsByQrToken(request.getQrToken())) {
            throw new DuplicateResourceException("QR Session with this token already exists.");
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id : " + request.getCourseId()));

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id : " + request.getFacultyId()));

        QRSession qrSession = qrSessionMapper.toEntity(request);
        qrSession.setCourse(course);
        qrSession.setFaculty(faculty);

        if (request.getClassroomId() != null) {
            Classroom classroom = classroomRepository.findById(request.getClassroomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id : " + request.getClassroomId()));
            qrSession.setClassroom(classroom);
        }

        QRSession savedQRSession = qrSessionRepository.save(qrSession);

        return qrSessionMapper.toResponse(savedQRSession);
    }

    @Override
    @Transactional(readOnly = true)
    public QRSessionResponse getQRSessionById(UUID id) {

        QRSession qrSession = qrSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QRSession not found with id : " + id));

        return qrSessionMapper.toResponse(qrSession);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QRSessionResponse> getAllQRSessions(Pageable pageable) {

        return qrSessionRepository.findAll(pageable)
                .map(qrSessionMapper::toResponse);
    }

    @Override
    public QRSessionResponse updateQRSession(UUID id, UpdateQRSessionRequest request) {

        QRSession qrSession = qrSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QRSession not found with id : " + id));

        if (!qrSession.getQrToken().equals(request.getQrToken()) && qrSessionRepository.existsByQrToken(request.getQrToken())) {
            throw new DuplicateResourceException("QR Session with this token already exists.");
        }

        if (request.getClassroomId() != null) {
            if (qrSession.getClassroom() == null || !qrSession.getClassroom().getId().equals(request.getClassroomId())) {
                Classroom classroom = classroomRepository.findById(request.getClassroomId())
                        .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id : " + request.getClassroomId()));
                qrSession.setClassroom(classroom);
            }
        } else {
            qrSession.setClassroom(null);
        }

        qrSessionMapper.updateQRSession(request, qrSession);

        QRSession updatedQRSession = qrSessionRepository.save(qrSession);

        return qrSessionMapper.toResponse(updatedQRSession);
    }

    @Override
    public void deleteQRSession(UUID id) {

        QRSession qrSession = qrSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QRSession not found with id : " + id));

        qrSessionRepository.delete(qrSession);
    }
}
