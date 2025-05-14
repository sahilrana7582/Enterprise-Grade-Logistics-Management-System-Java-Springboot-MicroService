package com.example.expense_tracker.common;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentCreatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    // Event metadata
    private String eventId;                  // UUID for unique event tracking
    private String eventType;               // e.g., "SHIPMENT_CREATED"
    private LocalDateTime eventTimestamp;   // When the event occurred

    // Shipment core data
    private Long shipmentId;
    private String trackingNumber;

    private Long senderId;
    private String senderEmail;

    private Long receiverId;
    private String receiverEmail;

    private String originCity;
    private String destinationCity;

    private String shipmentType;            // Enum value (e.g., "EXPRESS")
    private Double weightKg;
    private BigDecimal cost;
    private LocalDateTime expectedDeliveryDate;

    private String status;                  // Shipment status (e.g., "CREATED")
}

