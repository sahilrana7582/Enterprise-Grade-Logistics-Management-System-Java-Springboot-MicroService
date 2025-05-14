package com.logistic_management_system.driver_assignment_service.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long shipmentId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    private LocalDateTime assignedTime;
}