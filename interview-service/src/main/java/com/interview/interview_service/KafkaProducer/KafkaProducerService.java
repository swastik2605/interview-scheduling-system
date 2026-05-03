package com.interview.interview_service.KafkaProducer;



import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "interview-booked";

    public void sendBookingEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Kafka event sent: " + message);
    }
}