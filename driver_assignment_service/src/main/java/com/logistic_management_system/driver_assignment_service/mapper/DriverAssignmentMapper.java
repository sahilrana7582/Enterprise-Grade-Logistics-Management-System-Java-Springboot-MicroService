package com.logistic_management_system.driver_assignment_service.mapper;


import com.logistic_management_system.driver_assignment_service.dto.AssignedShipmentDTO;
import com.logistic_management_system.driver_assignment_service.dto.DriverRequestDto;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentResponseDTO;
import com.logistic_management_system.driver_assignment_service.model.AssignedShipment;
import com.logistic_management_system.driver_assignment_service.model.Driver;

public class DriverAssignmentMapper {

    public static DriverRequestDto toDriverDTO(Driver driver) {
        DriverRequestDto dto = new DriverRequestDto();
        dto.setName(driver.getName());
        return dto;
    }

    public static AssignedShipmentDTO toAssignedShipmentDTO(AssignedShipment entity) {
        AssignedShipmentDTO dto = new AssignedShipmentDTO();
        dto.setId(entity.getId());
        dto.setShipmentId(entity.getShipmentId());
        dto.setAssignedTime(entity.getAssignedTime());
        dto.setDriver(toDriverDTO(entity.getDriver()));
        return dto;
    }

    public static ShipmentAssignmentResponseDTO toResponseDTO(AssignedShipment assignment, String status) {
        ShipmentAssignmentResponseDTO dto = new ShipmentAssignmentResponseDTO();
        dto.setAssignmentId(assignment.getId());
        dto.setShipmentId(assignment.getShipmentId());
        dto.setDriverName(assignment.getDriver().getName());
        dto.setAssignedTime(assignment.getAssignedTime());
        dto.setStatus(status);
        return dto;
    }

}

