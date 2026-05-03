package com.interview.interview_service.KafkaConsumer;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "interview-booked", groupId = "interview-group")
    public void consume(String message) {
        System.out.println("Notification received: " + message);
        // In real world: send email/SMS here
    }
}
