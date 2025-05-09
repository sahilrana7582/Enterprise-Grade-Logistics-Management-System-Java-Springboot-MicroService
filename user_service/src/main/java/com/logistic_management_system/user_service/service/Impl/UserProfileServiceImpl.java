package com.logistic_management_system.user_service.service.Impl;

import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import com.logistic_management_system.user_service.exception.ResourceNotFoundException;
import com.logistic_management_system.user_service.mapper.UserProfileMapper;
import com.logistic_management_system.user_service.model.User;
import com.logistic_management_system.user_service.model.UserProfile;
import com.logistic_management_system.user_service.repository.UserProfileRepository;
import com.logistic_management_system.user_service.repository.UserRepository;
import com.logistic_management_system.user_service.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper mapper;
    private final UserRepository userRepository;

    @Override
    public UserProfile createProfile(Long userId, UserProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId, HttpStatus.NOT_FOUND));

        UserProfile profile = mapper.mapToEntity(dto);
        profile.setUser(user);
        user.setProfile(profile);

        User savedUser = userRepository.save(user);
        return savedUser.getProfile();
    }

    @Override
    public UserProfile updateProfile(Long userId, UserProfileDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId, HttpStatus.NOT_FOUND));

        UserProfile existingProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for userId: " + userId, HttpStatus.NOT_FOUND));

        mapper.updateEntity(existingProfile, dto);
        return userProfileRepository.save(existingProfile);
    }


    @Override
    public UserProfile getProfileById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId, HttpStatus.NOT_FOUND));

        return userProfileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for userId: " + userId, HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId, HttpStatus.NOT_FOUND));

        UserProfile profile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile not found for userId: " + userId, HttpStatus.NOT_FOUND));

        userProfileRepository.delete(profile);
    }
}
