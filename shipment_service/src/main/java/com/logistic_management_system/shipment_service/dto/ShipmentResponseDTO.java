package com.logistic_management_system.shipment_service.dto;

import com.logistic_management_system.shipment_service.enums.ShipmentStatus;
import com.logistic_management_system.shipment_service.enums.ShipmentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentResponseDTO {

    private Long id;

    private String trackingNumber;

    private Long senderId;

    private Long receiverId;

    private AddressDTO origin;

    private AddressDTO destination;

    private ShipmentType shipmentType;

    private ShipmentStatus status;

    private Double weightKg;

    private BigDecimal cost;

    private LocalDate expectedDeliveryDate;

    private Long assignedDriverId;
}
