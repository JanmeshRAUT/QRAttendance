package com.janmesh.attendance.enrollment.service.impl;

import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import com.janmesh.attendance.course.entity.Course;
import com.janmesh.attendance.course.repository.CourseRepository;
import com.janmesh.attendance.enrollment.dto.request.CreateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.request.UpdateEnrollmentRequest;
import com.janmesh.attendance.enrollment.dto.response.EnrollmentResponse;
import com.janmesh.attendance.enrollment.entity.Enrollment;
import com.janmesh.attendance.enrollment.mapper.EnrollmentMapper;
import com.janmesh.attendance.enrollment.repository.EnrollmentRepository;
import com.janmesh.attendance.enrollment.service.interfaces.EnrollmentService;
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
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {

        if (enrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new DuplicateResourceException("Student is already enrolled in this course.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id : " + request.getCourseId()));

        Enrollment enrollment = enrollmentMapper.toEntity(request);
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(savedEnrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public EnrollmentResponse getEnrollmentById(UUID id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id : " + id));

        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnrollmentResponse> getAllEnrollments(Pageable pageable) {

        return enrollmentRepository.findAll(pageable)
                .map(enrollmentMapper::toResponse);
    }

    @Override
    public EnrollmentResponse updateEnrollment(UUID id, UpdateEnrollmentRequest request) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id : " + id));

        // Note: Updating composite unique keys is generally discouraged, but implemented here for standard CRUD consistency
        if (!enrollment.getStudent().getId().equals(request.getStudentId()) || 
            !enrollment.getCourse().getId().equals(request.getCourseId())) {
            
            if (enrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
                throw new DuplicateResourceException("Another enrollment already exists for this student and course.");
            }

            Student student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found with id : " + request.getStudentId()));

            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id : " + request.getCourseId()));

            enrollment.setStudent(student);
            enrollment.setCourse(course);
        }

        enrollmentMapper.updateEnrollment(request, enrollment);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(UUID id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id : " + id));

        enrollmentRepository.delete(enrollment);
    }
}
