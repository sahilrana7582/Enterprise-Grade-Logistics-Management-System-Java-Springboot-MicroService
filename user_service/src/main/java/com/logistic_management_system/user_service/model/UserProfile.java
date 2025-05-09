package com.logistic_management_system.user_service.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

