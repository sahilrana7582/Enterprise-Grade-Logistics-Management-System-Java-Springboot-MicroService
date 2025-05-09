package com.logistic_management_system.user_service.service.interfaces;

import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import com.logistic_management_system.user_service.model.UserProfile;

public interface UserProfileService {
    UserProfile createProfile(Long id, UserProfileDTO dto);
    UserProfile updateProfile(Long id, UserProfileDTO dto);
    UserProfile getProfileById(Long id);
    void deleteProfile(Long id);
}
