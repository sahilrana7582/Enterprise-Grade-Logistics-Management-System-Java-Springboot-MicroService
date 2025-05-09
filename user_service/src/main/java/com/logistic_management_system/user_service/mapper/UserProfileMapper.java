package com.logistic_management_system.user_service.mapper;

import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import com.logistic_management_system.user_service.model.UserProfile;
import org.springframework.stereotype.Component;


@Component
public class UserProfileMapper {

    public UserProfile mapToEntity(UserProfileDTO dto) {
        return UserProfile.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .postalCode(dto.getPostalCode())
                .build();
    }

    public void updateEntity(UserProfile entity, UserProfileDTO dto) {
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setPostalCode(dto.getPostalCode());
    }
}
