package com.janmesh.attendance.classroom.controller;

import com.janmesh.attendance.classroom.dto.request.CreateClassroomRequest;
import com.janmesh.attendance.classroom.dto.request.UpdateClassroomRequest;
import com.janmesh.attendance.classroom.dto.response.ClassroomResponse;
import com.janmesh.attendance.classroom.service.interfaces.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomResponse> createClassroom(
            @Valid @RequestBody CreateClassroomRequest request) {

        ClassroomResponse response = classroomService.createClassroom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponse> getClassroomById(
            @PathVariable UUID id) {

        ClassroomResponse response = classroomService.getClassroomById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ClassroomResponse>> getAllClassrooms(
            Pageable pageable) {

        Page<ClassroomResponse> response = classroomService.getAllClassrooms(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponse> updateClassroom(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateClassroomRequest request) {

        ClassroomResponse response = classroomService.updateClassroom(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(
            @PathVariable UUID id) {

        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
