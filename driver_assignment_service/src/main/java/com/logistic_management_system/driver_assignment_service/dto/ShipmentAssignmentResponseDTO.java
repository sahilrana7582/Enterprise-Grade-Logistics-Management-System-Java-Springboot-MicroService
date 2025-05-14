package com.logistic_management_system.driver_assignment_service.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentAssignmentResponseDTO {
    private Long assignmentId;
    private Long shipmentId;
    private String driverName;
    private LocalDateTime assignedTime;
    private String status;
}
