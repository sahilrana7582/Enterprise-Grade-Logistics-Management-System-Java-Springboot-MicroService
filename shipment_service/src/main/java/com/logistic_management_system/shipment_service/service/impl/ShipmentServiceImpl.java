package com.logistic_management_system.shipment_service.service.impl;

import com.example.expense_tracker.common.ShipmentCreatedEvent;
import com.logistic_management_system.shipment_service.config.KafkaProducerTemplate;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final KafkaProducerTemplate kafkaProducerTemplate;

    @Override
    public ShipmentResponseDTO createShipment(CreateShipmentRequestDTO requestDTO) {
        Shipment shipment = ShipmentMapper.toShipment(requestDTO);
        Shipment saved = shipmentRepository.save(shipment);
        ShipmentCreatedEvent event = new ShipmentCreatedEvent();
        event.setShipmentId(saved.getId());
        event.setTrackingNumber(saved.getTrackingNumber());
        event.setSenderId(saved.getSenderId());
        event.setSenderEmail("i.sahil8968627582@gmail.com");
        event.setReceiverId(saved.getReceiverId());
        event.setReceiverEmail("i.sahil0001@gmail.com");
        event.setOriginCity(saved.getOrigin().getCity());
        event.setDestinationCity(saved.getDestination().getCity());
        event.setShipmentType(saved.getShipmentType().toString());
        event.setWeightKg(saved.getWeightKg());
        event.setCost(saved.getCost());
        event.setExpectedDeliveryDate(LocalDateTime.now().plusDays(5));
        event.setStatus(saved.getStatus().toString());
        event.setEventId(UUID.randomUUID().toString());
        event.setShipmentType("SHIPMENT_CREATED");

        kafkaProducerTemplate.sendShipmentCreatedEvent(event);


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
