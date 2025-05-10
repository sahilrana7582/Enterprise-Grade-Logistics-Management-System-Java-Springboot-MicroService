package com.logistic_management_system.shipment_service.service;

import com.logistic_management_system.shipment_service.dto.*;

import java.util.List;

public interface ShipmentService {
    ShipmentResponseDTO createShipment(CreateShipmentRequestDTO requestDTO);
    ShipmentResponseDTO getShipmentByTrackingNumber(String trackingNumber);
    List<ShipmentResponseDTO> getAllShipments();
    ShipmentResponseDTO updateShipment(String trackingNumber, UpdateShipmentRequestDTO updateDTO);
    void deleteShipment(String trackingNumber);
}
