package com.logistic_management_system.shipment_service.mapper;

import com.logistic_management_system.shipment_service.dto.*;
import com.logistic_management_system.shipment_service.enums.ShipmentStatus;
import com.logistic_management_system.shipment_service.model.Address;
import com.logistic_management_system.shipment_service.model.Shipment;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class ShipmentMapper {

    public static Address toAddress(AddressDTO dto) {
        if (dto == null) return null;

        return Address.builder()
                .addressLine(dto.getAddressLine())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .postalCode(dto.getPostalCode())
                .build();
    }

    public static AddressDTO toAddressDTO(Address entity) {
        if (entity == null) return null;

        return AddressDTO.builder()
                .addressLine(entity.getAddressLine())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .build();
    }

    // Convert CreateShipmentRequestDTO -> Shipment
    public static Shipment toShipment(CreateShipmentRequestDTO dto) {
        if (dto == null) return null;

        return Shipment.builder()
                .trackingNumber(generateTrackingNumber())
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .origin(toAddress(dto.getOrigin()))
                .destination(toAddress(dto.getDestination()))
                .shipmentType(dto.getShipmentType())
                .status(ShipmentStatus.PENDING) // default
                .weightKg(dto.getWeightKg())
                .cost(dto.getCost())
                .expectedDeliveryDate(dto.getExpectedDeliveryDate())
                .build();
    }

    public static ShipmentResponseDTO toResponseDTO(Shipment shipment) {
        if (shipment == null) return null;

        return ShipmentResponseDTO.builder()
                .id(shipment.getId())
                .trackingNumber(shipment.getTrackingNumber())
                .senderId(shipment.getSenderId())
                .receiverId(shipment.getReceiverId())
                .origin(toAddressDTO(shipment.getOrigin()))
                .destination(toAddressDTO(shipment.getDestination()))
                .shipmentType(shipment.getShipmentType())
                .status(shipment.getStatus())
                .weightKg(shipment.getWeightKg())
                .cost(shipment.getCost())
                .expectedDeliveryDate(shipment.getExpectedDeliveryDate())
                .assignedDriverId(shipment.getAssignedDriverId())
                .build();
    }

    // Update Shipment Entity from UpdateShipmentRequestDTO
    public static void updateShipmentFromDTO(UpdateShipmentRequestDTO dto, Shipment shipment) {
        if (dto == null || shipment == null) return;

        if (dto.getStatus() != null) {
            shipment.setStatus(dto.getStatus());
        }

        if (dto.getCost() != null) {
            shipment.setCost(dto.getCost());
        }

        if (dto.getAssignedDriverId() != null) {
            shipment.setAssignedDriverId(dto.getAssignedDriverId());
        }

        if (dto.getExpectedDeliveryDate() != null) {
            shipment.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        }
    }

    private static String generateTrackingNumber() {
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
