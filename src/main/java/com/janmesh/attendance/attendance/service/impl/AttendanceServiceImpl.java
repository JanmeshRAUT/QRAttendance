package com.janmesh.attendance.attendance.service.impl;

import com.janmesh.attendance.attendance.dto.request.CreateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.request.UpdateAttendanceRequest;
import com.janmesh.attendance.attendance.dto.response.AttendanceResponse;
import com.janmesh.attendance.attendance.entity.Attendance;
import com.janmesh.attendance.attendance.mapper.AttendanceMapper;
import com.janmesh.attendance.attendance.repository.AttendanceRepository;
import com.janmesh.attendance.attendance.service.interfaces.AttendanceService;
import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import com.janmesh.attendance.qrsession.entity.QRSession;
import com.janmesh.attendance.qrsession.repository.QRSessionRepository;
import com.janmesh.attendance.student.entity.Student;
import com.janmesh.attendance.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final QRSessionRepository qrSessionRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponse createAttendance(CreateAttendanceRequest request) {

        if (attendanceRepository.existsByStudentIdAndQrSessionId(request.getStudentId(), request.getQrSessionId())) {
            throw new DuplicateResourceException("Attendance already marked for this student in this session.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + request.getStudentId()));

        QRSession qrSession = qrSessionRepository.findById(request.getQrSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("QRSession not found with id : " + request.getQrSessionId()));

        Attendance attendance = attendanceMapper.toEntity(request);
        attendance.setStudent(student);
        attendance.setQrSession(qrSession);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceResponse getAttendanceById(UUID id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id : " + id));

        return attendanceMapper.toResponse(attendance);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceResponse> getAllAttendances(Pageable pageable) {

        return attendanceRepository.findAll(pageable)
                .map(attendanceMapper::toResponse);
    }

    @Override
    public AttendanceResponse updateAttendance(UUID id, UpdateAttendanceRequest request) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id : " + id));

        attendanceMapper.updateAttendance(request, attendance);

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(updatedAttendance);
    }

    @Override
    public void deleteAttendance(UUID id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id : " + id));

        attendanceRepository.delete(attendance);
    }
}
