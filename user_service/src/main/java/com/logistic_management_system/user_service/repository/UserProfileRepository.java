package com.logistic_management_system.user_service.repository;

import com.logistic_management_system.user_service.model.User;
import com.logistic_management_system.user_service.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUser(User user);

}
