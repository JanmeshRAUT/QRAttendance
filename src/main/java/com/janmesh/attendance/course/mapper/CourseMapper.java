package com.janmesh.attendance.course.mapper;

import com.janmesh.attendance.course.dto.request.CreateCourseRequest;
import com.janmesh.attendance.course.dto.request.UpdateCourseRequest;
import com.janmesh.attendance.course.dto.response.CourseResponse;
import com.janmesh.attendance.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CreateCourseRequest request);

    @Mapping(target = "facultyId", source = "faculty.id")
    CourseResponse toResponse(Course course);

    void updateCourse(UpdateCourseRequest request,
                      @MappingTarget Course course);

}
