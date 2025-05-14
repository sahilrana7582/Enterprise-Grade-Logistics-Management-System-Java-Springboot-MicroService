package com.logistic_management_system.driver_assignment_service.service.impl;

import com.example.expense_tracker.common.ShipmentCreatedEvent;
import com.logistic_management_system.driver_assignment_service.dto.DriverRequestDto;
import com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentResponseDTO;
import com.logistic_management_system.driver_assignment_service.mapper.DriverAssignmentMapper;
import com.logistic_management_system.driver_assignment_service.model.AssignedShipment;
import com.logistic_management_system.driver_assignment_service.model.Driver;
import com.logistic_management_system.driver_assignment_service.repository.AssignedShipmentRepository;
import com.logistic_management_system.driver_assignment_service.repository.DriverRepository;
import com.logistic_management_system.driver_assignment_service.service.DriverAssignmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverAssignmentServiceImpl implements DriverAssignmentService {

    private final DriverRepository driverRepository;
    private final AssignedShipmentRepository assignmentRepository;

    @Override
    @Transactional
    public ShipmentAssignmentResponseDTO assignDriverToShipment(com.logistic_management_system.driver_assignment_service.dto.ShipmentAssignmentRequestDTO request) {
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with ID: " + request.getDriverId()));

        AssignedShipment assignment = new AssignedShipment();
        assignment.setShipmentId(request.getShipmentId());
        assignment.setDriver(driver);
        assignment.setAssignedTime(LocalDateTime.now());

        AssignedShipment saved = assignmentRepository.save(assignment);
        return DriverAssignmentMapper.toResponseDTO(saved, "ASSIGNED");
    }

    @Override
    public ShipmentAssignmentResponseDTO getAssignmentByShipmentId(Long shipmentId) {
        AssignedShipment assignment = assignmentRepository.findByShipmentId(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("No driver assignment found for shipment ID: " + shipmentId));
        return DriverAssignmentMapper.toResponseDTO(assignment, "FOUND");
    }

    @KafkaListener(
            topics = "shipment_created_topic",
            groupId = "driver-assignment-service",
            containerFactory = "shipmentConsumerFactory"
    )
    public void handleShipmentCreated(ShipmentCreatedEvent event) {
        log.info("Kafka Event Received - ShipmentCreatedEvent: {}", event);

        try {

            List<Driver> drivers = driverRepository.findAll();

            Driver selectedDriver = drivers.get((int) Math.floor(Math.random() * drivers.size()));

            if(selectedDriver == null) {
                log.error("No drivers available for shipment ID: {}", event.getShipmentId());
                return;
            }


            log.info("Driver selected - Driver ID: {}", selectedDriver.getName());

            AssignedShipment assignment = new AssignedShipment();
            assignment.setShipmentId(event.getShipmentId());
            assignment.setDriver(selectedDriver);
            assignment.setAssignedTime(LocalDateTime.now());

            AssignedShipment saved = assignmentRepository.save(assignment);

            log.info("Driver assigned successfully - Driver ID: {}, Shipment ID: {}",
                    selectedDriver.getId(), event.getShipmentId());

        } catch (Exception ex) {
            log.error("Error processing ShipmentCreatedEvent for shipment ID: {} - {}",
                    event.getShipmentId(), ex.getMessage(), ex);
        }
    }


    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found with ID: " + driverId));
    }

    public Driver saveDriver(DriverRequestDto driver) {
        Driver newDriver = new Driver();
        newDriver.setName(driver.getName());
        return driverRepository.save(newDriver);
    }
}
