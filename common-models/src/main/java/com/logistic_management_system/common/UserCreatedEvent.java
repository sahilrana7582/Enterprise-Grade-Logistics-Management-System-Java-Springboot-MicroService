package com.logistic_management_system.common;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedEvent {

    private String eventId;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;

}