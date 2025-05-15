package com.logistic_management_system.email_service.service;

import com.logistic_management_system.common.DriverAssignedEvent;
import com.logistic_management_system.common.ShipmentCreatedEvent;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
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

    @Retry(name = "emailRetry", fallbackMethod = "fallbackEmailSender")
    public <T> void sendEmail(T event) {
        try {
            if (event instanceof ShipmentCreatedEvent shipmentCreatedEvent) {
                System.out.println("📦 Processing shipment ID: " + shipmentCreatedEvent.getShipmentId()
                        + " on Thread: " + Thread.currentThread().getName());

                String subject = "Shipment Created: " + shipmentCreatedEvent.getShipmentId();
                String text = "Shipment ID: " + shipmentCreatedEvent.getShipmentId() + "\n" +
                        "Tracking Number: " + shipmentCreatedEvent.getTrackingNumber() + "\n" +
                        "Sender ID: " + shipmentCreatedEvent.getSenderId() + "\n" +
                        "Receiver ID: " + shipmentCreatedEvent.getReceiverId() + "\n" +
                        "Origin City: " + shipmentCreatedEvent.getOriginCity() + "\n" +
                        "Destination City: " + shipmentCreatedEvent.getDestinationCity() + "\n" +
                        "Shipment Type: " + shipmentCreatedEvent.getShipmentType() + "\n" +
                        "Weight (kg): " + shipmentCreatedEvent.getWeightKg() + "\n" +
                        "Cost: $" + shipmentCreatedEvent.getCost() + "\n" +
                        "Expected Delivery Date: " + shipmentCreatedEvent.getExpectedDeliveryDate() + "\n" +
                        "Status: " + shipmentCreatedEvent.getStatus();

                System.out.println("✅ Email sent to " + shipmentCreatedEvent.getReceiverEmail());
            }


            System.out.println("📦 Processing shipment ID: " + event + " on Thread: " + Thread.currentThread().getName());
            System.out.println("Looking For Available Driver");
            Thread.sleep(5000);


            if (event instanceof DriverAssignedEvent) {
                DriverAssignedEvent driverAssignedEvent = (DriverAssignedEvent) event;

                System.out.println("📦 Processing driver assignment for shipment ID: "
                        + driverAssignedEvent.getShipmentId()
                        + " on Thread: " + Thread.currentThread().getName());


                System.out.println("========= Driver Assigned Info =========");
                System.out.println("Driver ID: " + driverAssignedEvent.getDriverId());
                System.out.println("Shipment Id: " + driverAssignedEvent.getShipmentId());
                System.out.println("Assigned Time: " + driverAssignedEvent.getAssignedTime());

                System.out.println("========================================");

            }



        } catch (Exception e) {
            System.err.println("❌ Failed to send email for shipment ID: " + event);
            e.printStackTrace();
        }

    }


    public void fallbackEmailSender(ShipmentCreatedEvent event, Throwable ex) {
        System.err.println("❌ Failed to send email after retries for Shipment ID: " + event.getShipmentId());
        System.err.println("❌ Reason: " + ex.getMessage());
    }




    @KafkaListener(
            topics = "driver_assigned_topic",
            groupId = "email-service",
            containerFactory = "driverAssignmentConsumerFactory"
    )
    public void handleDriverAssignedEvent(DriverAssignedEvent event) {
        emailExecutor.execute(() -> sendEmail(event));
    }
}
