package com.logistic_management_system.driver_assignment_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedShipmentDTO {
    private Long id;
    private Long shipmentId;
    private DriverRequestDto driver;
    private LocalDateTime assignedTime;
}
