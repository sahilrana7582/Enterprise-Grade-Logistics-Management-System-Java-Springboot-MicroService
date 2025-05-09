package com.logistic_management_system.user_service.dto.user.request;


import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Data
public class UserRequestDTO {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private boolean active = true;

    @NotNull(message = "Profile is required")
    private UserProfileDTO profile;

    private Set<String> roles;
}

