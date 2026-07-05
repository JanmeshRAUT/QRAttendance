package com.janmesh.attendance.user.dto.response;

import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private Role role;
    private AccountStatus accountStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
