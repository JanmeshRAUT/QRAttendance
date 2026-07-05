package com.janmesh.attendance.user.controller;

import com.janmesh.attendance.user.dto.request.CreateUserRequest;
import com.janmesh.attendance.user.dto.request.LoginRequest;
import com.janmesh.attendance.user.dto.request.UpdateUserRequest;
import com.janmesh.attendance.user.dto.response.UserResponse;
import com.janmesh.attendance.user.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        UserResponse userResponse = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable UUID id){
        UserResponse userResponse=userService.getUserByID(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> responses=userService.listUsers();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request,@PathVariable UUID id){
        UserResponse response=userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request){
        UserResponse userResponse = userService.login(request);
        return ResponseEntity.ok(userResponse);
    }
}
