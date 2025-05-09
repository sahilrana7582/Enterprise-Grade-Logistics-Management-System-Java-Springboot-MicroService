package com.logistic_management_system.user_service.dto.user.response;


import com.logistic_management_system.user_service.dto.user.request.RoleDTO;
import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private boolean active;
    private UserProfileDTO profile;
    private Set<RoleDTO> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
