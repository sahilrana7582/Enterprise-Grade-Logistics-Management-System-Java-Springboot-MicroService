package com.logistic_management_system.user_service.controller;

import com.logistic_management_system.user_service.dto.user_profile.request.UserProfileDTO;
import com.logistic_management_system.user_service.model.UserProfile;
import com.logistic_management_system.user_service.service.interfaces.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> createProfile(@PathVariable Long userId, @RequestBody UserProfileDTO dto) {
        UserProfile profile = userProfileService.createProfile(userId, dto);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable Long userId, @RequestBody UserProfileDTO dto) {
        UserProfile updatedProfile = userProfileService.updateProfile(userId, dto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> getProfileByUserId(@PathVariable Long userId) {
        UserProfile profile = userProfileService.getProfileById(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {
        userProfileService.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
