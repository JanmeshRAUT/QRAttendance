package com.janmesh.attendance.student.mapper;

import com.janmesh.attendance.student.dto.request.CreateStudentRequest;
import com.janmesh.attendance.student.dto.request.UpdateStudentRequest;
import com.janmesh.attendance.student.dto.response.StudentResponse;
import com.janmesh.attendance.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "user.id", source = "userId")
    Student toEntity(CreateStudentRequest request);

    @Mapping(target = "userId", source = "user.id")
    StudentResponse toResponse(Student student);

    void updateStudent(UpdateStudentRequest request,
                       @MappingTarget Student student);

}
