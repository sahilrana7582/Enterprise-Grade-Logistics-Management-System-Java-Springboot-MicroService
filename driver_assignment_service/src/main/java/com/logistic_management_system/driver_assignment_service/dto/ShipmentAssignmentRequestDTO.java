package com.logistic_management_system.driver_assignment_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipmentAssignmentRequestDTO {

    @NotNull(message = "Shipment ID is required")
    private Long shipmentId;

    @NotNull(message = "Driver ID is required")
    private Long driverId;
}
