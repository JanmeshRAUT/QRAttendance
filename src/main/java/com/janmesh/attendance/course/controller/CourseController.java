package com.janmesh.attendance.course.controller;

import com.janmesh.attendance.course.dto.request.CreateCourseRequest;
import com.janmesh.attendance.course.dto.request.UpdateCourseRequest;
import com.janmesh.attendance.course.dto.response.CourseResponse;
import com.janmesh.attendance.course.service.interfaces.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(
            @Valid @RequestBody CreateCourseRequest request) {

        CourseResponse response = courseService.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable UUID id) {

        CourseResponse response = courseService.getCourseById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllCourses(
            Pageable pageable) {

        Page<CourseResponse> response = courseService.getAllCourses(pageable);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCourseRequest request) {

        CourseResponse response = courseService.updateCourse(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable UUID id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}
