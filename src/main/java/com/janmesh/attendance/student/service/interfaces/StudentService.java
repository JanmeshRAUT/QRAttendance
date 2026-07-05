package com.janmesh.attendance.student.service.interfaces;

import com.janmesh.attendance.student.dto.request.CreateStudentRequest;
import com.janmesh.attendance.student.dto.request.UpdateStudentRequest;
import com.janmesh.attendance.student.dto.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StudentService {

    StudentResponse createStudent(CreateStudentRequest request);

    StudentResponse getStudentById(UUID id);

    Page<StudentResponse> getAllStudents(Pageable pageable);

    StudentResponse updateStudent(UUID id, UpdateStudentRequest request);

    void deleteStudent(UUID id);

}
