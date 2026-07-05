package com.janmesh.attendance.user.service.impl;

import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.user.dto.request.CreateUserRequest;
import com.janmesh.attendance.user.dto.request.LoginRequest;
import com.janmesh.attendance.user.dto.request.UpdateUserRequest;
import com.janmesh.attendance.user.dto.response.UserResponse;
import com.janmesh.attendance.user.entity.User;
import com.janmesh.attendance.user.exception.*;
import com.janmesh.attendance.user.mapper.UserMapper;
import com.janmesh.attendance.user.repository.UserRepository;
import com.janmesh.attendance.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExist("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExist("Email already exists");
        }
        User user = userMapper.toEntity(request);
        user.setAccountStatus(AccountStatus.ACTIVE);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserByID(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> listUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequest request){
        User user=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!user.getUsername().equals(request.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExist("Username already exists");
        }
        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExist("Email already exists");
        }
        userMapper.updateUser(request,user);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Invalid Username and Password"));

        if(!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Invalid Username and Password");
        }
        return userMapper.toResponse(user);
    }
}
