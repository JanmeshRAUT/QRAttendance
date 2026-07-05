package com.janmesh.attendance.course.service;

import com.janmesh.attendance.course.dto.request.CreateCourseRequest;
import com.janmesh.attendance.course.dto.request.UpdateCourseRequest;
import com.janmesh.attendance.course.dto.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(UUID id);

    Page<CourseResponse> getAllCourses(Pageable pageable);

    CourseResponse updateCourse(UUID id, UpdateCourseRequest request);

    void deleteCourse(UUID id);

}