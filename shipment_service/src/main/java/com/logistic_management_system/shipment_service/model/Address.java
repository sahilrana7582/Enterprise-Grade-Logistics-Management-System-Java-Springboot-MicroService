package com.logistic_management_system.shipment_service.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
