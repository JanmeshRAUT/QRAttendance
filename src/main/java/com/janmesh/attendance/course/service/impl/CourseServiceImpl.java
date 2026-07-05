package com.janmesh.attendance.course.service.impl;

import com.janmesh.attendance.common.exception.DuplicateResourceException;
import com.janmesh.attendance.common.exception.ResourceNotFoundException;
import com.janmesh.attendance.course.dto.request.CreateCourseRequest;
import com.janmesh.attendance.course.dto.request.UpdateCourseRequest;
import com.janmesh.attendance.course.dto.response.CourseResponse;
import com.janmesh.attendance.course.entity.Course;
import com.janmesh.attendance.course.mapper.CourseMapper;
import com.janmesh.attendance.course.repository.CourseRepository;
import com.janmesh.attendance.course.service.interfaces.CourseService;
import com.janmesh.attendance.faculty.entity.Faculty;
import com.janmesh.attendance.faculty.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists.");
        }

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Faculty not found with id : " + request.getFacultyId()));

        Course course = courseMapper.toEntity(request);

        course.setFaculty(faculty);

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toResponse(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourseById(UUID id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        return courseMapper.toResponse(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseResponse> getAllCourses(Pageable pageable) {

        return courseRepository.findAll(pageable)
                .map(courseMapper::toResponse);
    }

    @Override
    public CourseResponse updateCourse(UUID id, UpdateCourseRequest request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        courseMapper.updateCourse(request, course);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(UUID id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id : " + id));

        courseRepository.delete(course);
    }
}