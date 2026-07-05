package com.janmesh.attendance.user.mapper;

import com.janmesh.attendance.user.dto.request.CreateUserRequest;
import com.janmesh.attendance.user.dto.request.UpdateUserRequest;
import com.janmesh.attendance.user.dto.response.UserResponse;
import com.janmesh.attendance.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUserRequest request);
    UserResponse toResponse(User user);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
