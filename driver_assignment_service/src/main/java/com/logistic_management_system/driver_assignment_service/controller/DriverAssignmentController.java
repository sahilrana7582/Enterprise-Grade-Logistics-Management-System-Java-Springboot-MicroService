package com.logistic_management_system.driver_assignment_service.controller;

import com.logistic_management_system.driver_assignment_service.dto.DriverRequestDto;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentRequestDTO;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentResponseDTO;
import com.logistic_management_system.driver_assignment_service.model.Driver;
import com.logistic_management_system.driver_assignment_service.service.DriverAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assignments")
@RequiredArgsConstructor
@Slf4j
public class DriverAssignmentController {

    private final DriverAssignmentService driverAssignmentService;

    @PostMapping
    public ResponseEntity<ShipmentAssignmentResponseDTO> assignDriver(
            @Valid @RequestBody ShipmentAssignmentRequestDTO request) {
        log.info("Received request to assign driver: {}", request);
        ShipmentAssignmentResponseDTO response = driverAssignmentService.assignDriverToShipment(request);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentAssignmentResponseDTO> getAssignmentByShipmentId(
            @PathVariable Long shipmentId) {
        log.info("Fetching assignment for shipmentId: {}", shipmentId);
        ShipmentAssignmentResponseDTO response = driverAssignmentService.getAssignmentByShipmentId(shipmentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getAssignmentById(
            @PathVariable Long id) {
        log.info("Fetching assignment for id: {}", id);
        Driver response = driverAssignmentService.getDriverById(id);
        return ResponseEntity.ok(response);
    };

    @PostMapping("/driver")
    public ResponseEntity<Driver> saveDriver(@Valid @RequestBody DriverRequestDto driver) {
        log.info("Received request to save driver: {}", driver);
        Driver savedDriver = driverAssignmentService.saveDriver(driver);
        return ResponseEntity.ok(savedDriver);
    }
}
