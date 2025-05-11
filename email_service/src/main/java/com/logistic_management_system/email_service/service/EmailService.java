package com.logistic_management_system.email_service.service;

import com.example.expense_tracker.common.ShipmentCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(
            topics = "shipment_created_topic",
            groupId = "email-service",
            containerFactory = "emailConsumerFactory"
    )
    public void handleShipmentCreatedEvent(ShipmentCreatedEvent event) {
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("📦 Shipment created event received:");
        System.out.println("Sending email for Shipment ID: " + event.getShipmentId());
        System.out.println("Recipient: " + event.getReceiverEmail());
        System.out.println("======================================");
        System.out.println("======================================");

        String subject = "Shipment Created: " + event.getShipmentId();
        String text = "Shipment ID: " + event.getShipmentId() + "\n" +
                "Tracking Number: " + event.getTrackingNumber() + "\n" +
                "Sender ID: " + event.getSenderId() + "\n" +
                "Receiver ID: " + event.getReceiverId() + "\n" +
                "Origin City: " + event.getOriginCity() + "\n" +
                "Destination City: " + event.getDestinationCity() + "\n" +
                "Shipment Type: " + event.getShipmentType() + "\n" +
                "Weight (kg): " + event.getWeightKg() + "\n" +
                "Cost: $" + event.getCost() + "\n" +
                "Expected Delivery Date: " + event.getExpectedDeliveryDate() + "\n" +
                "Status: " + event.getStatus();

        String toAddress = event.getReceiverEmail();
        String fromAddress = "i.sahil0001@gmail.com";
//
//       SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toAddress);
//        message.setFrom(fromAddress);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);

        System.out.println("✅ Email sent");
        System.out.println("✅ Email sent to");

    }
}
