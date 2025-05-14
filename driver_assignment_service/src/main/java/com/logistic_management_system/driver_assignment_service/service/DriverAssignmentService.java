package com.logistic_management_system.driver_assignment_service.service;

import com.logistic_management_system.driver_assignment_service.dto.DriverRequestDto;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentRequestDTO;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentResponseDTO;
import com.logistic_management_system.driver_assignment_service.model.Driver;

public interface DriverAssignmentService {

    ShipmentAssignmentResponseDTO assignDriverToShipment(ShipmentAssignmentRequestDTO request);

    ShipmentAssignmentResponseDTO getAssignmentByShipmentId(Long shipmentId);
    Driver saveDriver(DriverRequestDto driver);
    Driver getDriverById(Long driverId);

}
