package com.janmesh.attendance.user.service.interfaces;

import com.janmesh.attendance.user.dto.request.CreateUserRequest;
import com.janmesh.attendance.user.dto.request.LoginRequest;
import com.janmesh.attendance.user.dto.request.UpdateUserRequest;
import com.janmesh.attendance.user.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserByID(UUID id);
    List<UserResponse> listUsers();
    UserResponse updateUser(UUID id, UpdateUserRequest request);
    void deleteUser(UUID id);
    UserResponse login(LoginRequest request);
}
