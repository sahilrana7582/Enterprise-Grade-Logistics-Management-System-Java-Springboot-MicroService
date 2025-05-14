package com.logistic_management_system.email_service.service;

import com.example.expense_tracker.common.ShipmentCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("emailServiceExecutor")
    private Executor emailExecutor;

    @KafkaListener(
            topics = "shipment_created_topic",
            groupId = "email-service",
            containerFactory = "emailConsumerFactory"
    )
    public void handleShipmentCreatedEvent(ShipmentCreatedEvent event) {
        emailExecutor.execute(() -> sendEmail(event));
    }

    private void sendEmail(ShipmentCreatedEvent event) {
        try {
            System.out.println("📦 Processing shipment ID: " + event.getShipmentId() + " on Thread: " + Thread.currentThread().getName());
            Thread.sleep(10000);

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

//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(event.getReceiverEmail());
//            message.setFrom("i.sahil0001@gmail.com");
//            message.setSubject(subject);
//            message.setText(text);

//            mailSender.send(message);

            System.out.println("✅ Email sent to " + event.getReceiverEmail());

        } catch (Exception e) {
            System.err.println("❌ Failed to send email for shipment ID: " + event.getShipmentId());
            e.printStackTrace();
        }
    }
}
