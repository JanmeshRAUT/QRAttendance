package com.janmesh.attendance.classroom.service.interfaces;

import com.janmesh.attendance.classroom.dto.request.CreateClassroomRequest;
import com.janmesh.attendance.classroom.dto.request.UpdateClassroomRequest;
import com.janmesh.attendance.classroom.dto.response.ClassroomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClassroomService {

    ClassroomResponse createClassroom(CreateClassroomRequest request);

    ClassroomResponse getClassroomById(UUID id);

    Page<ClassroomResponse> getAllClassrooms(Pageable pageable);

    ClassroomResponse updateClassroom(UUID id, UpdateClassroomRequest request);

    void deleteClassroom(UUID id);

}
