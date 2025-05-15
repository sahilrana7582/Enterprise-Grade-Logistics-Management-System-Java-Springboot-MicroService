package com.logistic_management_system.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreatedEvent {

    private String eventId;
    private String userId;
    private String transactionType;
    private BigDecimal amount;

}
