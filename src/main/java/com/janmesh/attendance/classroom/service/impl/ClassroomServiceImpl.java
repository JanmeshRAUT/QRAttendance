package com.janmesh.attendance.classroom.service.impl;

import com.janmesh.attendance.classroom.dto.request.CreateClassroomRequest;
import com.janmesh.attendance.classroom.dto.request.UpdateClassroomRequest;
import com.janmesh.attendance.classroom.dto.response.ClassroomResponse;
import com.janmesh.attendance.classroom.entity.Classroom;
import com.janmesh.attendance.classroom.mapper.ClassroomMapper;
import com.janmesh.attendance.classroom.repository.ClassroomRepository;
import com.janmesh.attendance.classroom.service.interfaces.ClassroomService;
import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Override
    public ClassroomResponse createClassroom(CreateClassroomRequest request) {
        if (classroomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new DuplicateResourceException("Classroom room number already exists.");
        }

        Classroom classroom = classroomMapper.toEntity(request);
        Classroom savedClassroom = classroomRepository.save(classroom);
        return classroomMapper.toResponse(savedClassroom);
    }

    @Override
    @Transactional(readOnly = true)
    public ClassroomResponse getClassroomById(UUID id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id : " + id));
        return classroomMapper.toResponse(classroom);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClassroomResponse> getAllClassrooms(Pageable pageable) {
        return classroomRepository.findAll(pageable)
                .map(classroomMapper::toResponse);
    }

    @Override
    public ClassroomResponse updateClassroom(UUID id, UpdateClassroomRequest request) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id : " + id));

        classroomMapper.updateClassroom(request, classroom);
        Classroom updatedClassroom = classroomRepository.save(classroom);
        return classroomMapper.toResponse(updatedClassroom);
    }

    @Override
    public void deleteClassroom(UUID id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found with id : " + id));
        classroomRepository.delete(classroom);
    }
}
