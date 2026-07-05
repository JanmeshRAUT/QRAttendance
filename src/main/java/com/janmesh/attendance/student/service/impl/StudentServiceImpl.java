package com.janmesh.attendance.student.service.impl;

import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import com.janmesh.attendance.student.dto.request.CreateStudentRequest;
import com.janmesh.attendance.student.dto.request.UpdateStudentRequest;
import com.janmesh.attendance.student.dto.response.StudentResponse;
import com.janmesh.attendance.student.entity.Student;
import com.janmesh.attendance.student.mapper.StudentMapper;
import com.janmesh.attendance.student.repository.StudentRepository;
import com.janmesh.attendance.student.service.interfaces.StudentService;
import com.janmesh.attendance.user.entity.User;
import com.janmesh.attendance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new DuplicateResourceException("Roll number already exists.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        if (user.getStudent() != null) {
            throw new DuplicateResourceException("Student profile already exists for this user.");
        }

        Student student = studentMapper.toEntity(request);
        student.setUser(user);

        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(UUID id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentResponse> getAllStudents(Pageable pageable) {

        return studentRepository.findAll(pageable)
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse updateStudent(UUID id, UpdateStudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        studentMapper.updateStudent(request, student);

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(UUID id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
    }
}
