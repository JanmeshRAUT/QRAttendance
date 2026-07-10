package com.janmesh.attendance.qrsession.mapper;

import com.janmesh.attendance.qrsession.dto.request.CreateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.request.UpdateQRSessionRequest;
import com.janmesh.attendance.qrsession.dto.response.QRSessionResponse;
import com.janmesh.attendance.qrsession.entity.QRSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QRSessionMapper {

    QRSession toEntity(CreateQRSessionRequest request);

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "facultyId", source = "faculty.id")
    @Mapping(target = "classroomId", source = "classroom.id")
    QRSessionResponse toResponse(QRSession qrSession);

    void updateQRSession(UpdateQRSessionRequest request, @MappingTarget QRSession qrSession);
}
