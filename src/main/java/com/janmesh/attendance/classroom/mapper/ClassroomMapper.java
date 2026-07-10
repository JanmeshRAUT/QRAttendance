package com.janmesh.attendance.classroom.mapper;

import com.janmesh.attendance.classroom.dto.request.CreateClassroomRequest;
import com.janmesh.attendance.classroom.dto.request.UpdateClassroomRequest;
import com.janmesh.attendance.classroom.dto.response.ClassroomResponse;
import com.janmesh.attendance.classroom.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    Classroom toEntity(CreateClassroomRequest request);

    ClassroomResponse toResponse(Classroom classroom);

    void updateClassroom(UpdateClassroomRequest request, @MappingTarget Classroom classroom);
}
