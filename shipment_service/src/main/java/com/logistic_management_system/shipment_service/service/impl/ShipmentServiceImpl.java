package com.logistic_management_system.shipment_service.service.impl;

import com.logistic_management_system.shipment_service.dto.*;
import com.logistic_management_system.shipment_service.exception.ResourceNotFoundException;
import com.logistic_management_system.shipment_service.mapper.ShipmentMapper;
import com.logistic_management_system.shipment_service.model.Shipment;
import com.logistic_management_system.shipment_service.repository.ShipmentRepository;
import com.logistic_management_system.shipment_service.service.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public ShipmentResponseDTO createShipment(CreateShipmentRequestDTO requestDTO) {
        Shipment shipment = ShipmentMapper.toShipment(requestDTO);
        Shipment saved = shipmentRepository.save(shipment);
        return ShipmentMapper.toResponseDTO(saved);
    }

    @Override
    public ShipmentResponseDTO getShipmentByTrackingNumber(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for tracking number: " + trackingNumber, HttpStatus.NOT_FOUND));
        return ShipmentMapper.toResponseDTO(shipment);
    }

    @Override
    public List<ShipmentResponseDTO> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(ShipmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentResponseDTO updateShipment(String trackingNumber, UpdateShipmentRequestDTO updateDTO) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for tracking number: " + trackingNumber, HttpStatus.NOT_FOUND));

        ShipmentMapper.updateShipmentFromDTO(updateDTO, shipment);
        Shipment updated = shipmentRepository.save(shipment);

        return ShipmentMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteShipment(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found for tracking number: " + trackingNumber, HttpStatus.NOT_FOUND));
        shipmentRepository.delete(shipment);
    }
}
