package com.interview.interview_service.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.interview_service.Entity.InterviewSlot;
import com.interview.interview_service.KafkaProducer.KafkaProducerService;
import com.interview.interview_service.Repository.InterviewSlotRepository;
import org.springframework.cache.annotation.Cacheable;  // ✅ CORRECT
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class InterviewSlotService {

    private final InterviewSlotRepository slotRepository;
    private final KafkaProducerService kafkaProducerService;
    private final StringRedisTemplate redisTemplate;  // ← changed this
    private final ObjectMapper objectMapper;           // ← added this

    private static final String CACHE_KEY = "available_slots";

    public InterviewSlot addSlot(InterviewSlot slot) {
        InterviewSlot saved = slotRepository.save(slot);
        redisTemplate.delete(CACHE_KEY);
        return saved;
    }


    public List<InterviewSlot> getAvailableSlots() {
        try {
            String cached = redisTemplate.opsForValue().get(CACHE_KEY);
            if (cached != null) {
                System.out.println("Returning from Redis cache");
                List<InterviewSlot> list = objectMapper.readValue(cached,
                        objectMapper.getTypeFactory()
                                .constructCollectionType(List.class, InterviewSlot.class));
                return list;
            }
        } catch (Exception e) {
            System.out.println("Cache miss: " + e.getMessage());
        }

        List<InterviewSlot> slots = slotRepository.findByBookedFalse();
        try {
            redisTemplate.opsForValue().set(
                    CACHE_KEY,
                    objectMapper.writeValueAsString(slots),
                    5, TimeUnit.MINUTES
            );
        } catch (Exception e) {
            System.out.println("Cache write failed: " + e.getMessage());
        }
        return slots;
    }

    public InterviewSlot bookSlot(Long slotId, Long candidateId) {
        InterviewSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked!");
        }

        slot.setBooked(true);
        slot.setCandidateId(candidateId);
        InterviewSlot saved = slotRepository.save(slot);

        kafkaProducerService.sendBookingEvent(
                "Candidate " + candidateId + " booked slot " + slotId
        );
        redisTemplate.delete(CACHE_KEY);

        return saved;
    }
}