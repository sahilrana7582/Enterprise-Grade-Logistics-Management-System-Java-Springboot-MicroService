package com.logistic_management_system.shipment_service.dto;

import com.logistic_management_system.shipment_service.enums.ShipmentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateShipmentRequestDTO {

    private ShipmentStatus status;

    private BigDecimal cost;

    private Long assignedDriverId;

    private LocalDate expectedDeliveryDate;
}
