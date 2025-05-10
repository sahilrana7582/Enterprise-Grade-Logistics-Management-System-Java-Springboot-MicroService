package com.logistic_management_system.shipment_service.dto;

import com.logistic_management_system.shipment_service.enums.ShipmentType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateShipmentRequestDTO {

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotNull
    private AddressDTO origin;

    @NotNull
    private AddressDTO destination;

    @NotNull
    private ShipmentType shipmentType;

    @NotNull
    @Positive
    private Double weightKg;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal cost;

    @NotNull
    @FutureOrPresent
    private LocalDate expectedDeliveryDate;
}
