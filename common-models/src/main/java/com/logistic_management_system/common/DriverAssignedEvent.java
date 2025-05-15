package com.logistic_management_system.common;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverAssignedEvent {

    private String eventId;
    private Long shipmentId;
    private Long driverId;
    private LocalDateTime assignedTime;

}
