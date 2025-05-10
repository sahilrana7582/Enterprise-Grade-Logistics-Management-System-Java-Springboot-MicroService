package com.logistic_management_system.shipment_service.model;

import com.logistic_management_system.shipment_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tracking_number", nullable = false, unique = true, updatable = false)
    private String trackingNumber;

    // Sender (references User Service by ID or external userId)
    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    // If you need receiver as a user from system, also store receiverId
    @Embedded
    private ReceiverDetails receiver;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine", column = @Column(name = "origin_address")),
            @AttributeOverride(name = "city", column = @Column(name = "origin_city")),
            @AttributeOverride(name = "state", column = @Column(name = "origin_state")),
            @AttributeOverride(name = "country", column = @Column(name = "origin_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "origin_postal_code"))
    })
    private Address origin;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine", column = @Column(name = "destination_address")),
            @AttributeOverride(name = "city", column = @Column(name = "destination_city")),
            @AttributeOverride(name = "state", column = @Column(name = "destination_state")),
            @AttributeOverride(name = "country", column = @Column(name = "destination_country")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "destination_postal_code"))
    })
    private Address destination;

    @Column(name = "shipment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @Column(name = "weight_kg", nullable = false)
    private Double weightKg;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "expected_delivery_date", nullable = false)
    private LocalDate expectedDeliveryDate;

    @Column(name = "assigned_driver_id")
    private Long assignedDriverId;

}
