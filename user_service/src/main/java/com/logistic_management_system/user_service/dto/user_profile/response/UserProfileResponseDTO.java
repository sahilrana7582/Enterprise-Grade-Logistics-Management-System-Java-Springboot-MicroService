package com.logistic_management_system.user_service.dto.user_profile.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String profileImageUrl;
}

