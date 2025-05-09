package com.logistic_management_system.user_service.dto.user_profile.request;


import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format")
    private String phone;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City name must be less than 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State name must be less than 50 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country name must be less than 50 characters")
    private String country;

    @Pattern(regexp = "^[0-9]{6}", message = "Invalid postal code")
    private String postalCode;

}
