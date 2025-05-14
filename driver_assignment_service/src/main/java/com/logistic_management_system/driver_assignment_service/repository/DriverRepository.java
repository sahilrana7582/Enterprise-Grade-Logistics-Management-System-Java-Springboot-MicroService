package com.logistic_management_system.driver_assignment_service.repository;

import com.logistic_management_system.driver_assignment_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
