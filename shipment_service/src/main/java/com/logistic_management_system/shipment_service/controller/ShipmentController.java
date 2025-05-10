package com.logistic_management_system.shipment_service.controller;

import com.logistic_management_system.shipment_service.dto.CreateShipmentRequestDTO;
import com.logistic_management_system.shipment_service.dto.ShipmentResponseDTO;
import com.logistic_management_system.shipment_service.dto.UpdateShipmentRequestDTO;
import com.logistic_management_system.shipment_service.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    // ✅ CREATE Shipment
    @PostMapping
    public ResponseEntity<ShipmentResponseDTO> createShipment(
            @Valid @RequestBody CreateShipmentRequestDTO requestDTO) {
        ShipmentResponseDTO response = shipmentService.createShipment(requestDTO);
        return ResponseEntity.ok(response);
    }

    // ✅ GET Shipment by Tracking Number
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentByTrackingNumber(
            @PathVariable String trackingNumber) {
        ShipmentResponseDTO response = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(response);
    }

    // ✅ GET All Shipments
    @GetMapping
    public ResponseEntity<List<ShipmentResponseDTO>> getAllShipments() {
        List<ShipmentResponseDTO> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    // ✅ UPDATE Shipment
    @PutMapping("/{trackingNumber}")
    public ResponseEntity<ShipmentResponseDTO> updateShipment(
            @PathVariable String trackingNumber,
            @Valid @RequestBody UpdateShipmentRequestDTO updateDTO) {
        ShipmentResponseDTO response = shipmentService.updateShipment(trackingNumber, updateDTO);
        return ResponseEntity.ok(response);
    }

    // ✅ DELETE Shipment
    @DeleteMapping("/{trackingNumber}")
    public ResponseEntity<Void> deleteShipment(
            @PathVariable String trackingNumber) {
        shipmentService.deleteShipment(trackingNumber);
        return ResponseEntity.noContent().build();
    }
}
