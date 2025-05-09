package com.logistic_management_system.user_service.security.auth;


import com.logistic_management_system.user_service.model.Role;
import com.logistic_management_system.user_service.model.User;
import com.logistic_management_system.user_service.repository.RoleRepository;
import com.logistic_management_system.user_service.repository.UserRepository;
import com.logistic_management_system.user_service.security.auth.dto.LoginRequest;
import com.logistic_management_system.user_service.security.auth.dto.LoginResponse;
import com.logistic_management_system.user_service.security.auth.dto.RegisterRequest;
import com.logistic_management_system.user_service.security.auth.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setActive(true);

        // Assign roles
        user.setRoles(request.getRoles().stream().map(roleName -> {
            return roleRepository.findByName(roleName)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(roleName);
                        return roleRepository.save(newRole);
                    });
        }).collect(Collectors.toSet()));

        User savedUser = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setSuccess(savedUser != null);
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new LoginResponse(user.getEmail() + " logged in");
    }
}
