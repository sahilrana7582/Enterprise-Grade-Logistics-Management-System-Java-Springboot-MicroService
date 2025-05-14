package com.logistic_management_system.driver_assignment_service.repository;

import com.logistic_management_system.driver_assignment_service.model.AssignedShipment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AssignedShipmentRepository extends JpaRepository<AssignedShipment, Long> {

    Optional<AssignedShipment> findByShipmentId(Long shipmentId);
}
