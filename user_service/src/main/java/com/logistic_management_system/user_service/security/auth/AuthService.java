package com.logistic_management_system.user_service.security.auth;


import com.logistic_management_system.user_service.security.auth.dto.LoginRequest;
import com.logistic_management_system.user_service.security.auth.dto.LoginResponse;
import com.logistic_management_system.user_service.security.auth.dto.RegisterRequest;
import com.logistic_management_system.user_service.security.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}

