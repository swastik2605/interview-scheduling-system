package com.interview.interview_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Data
@Table(name = "interview_slots")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterviewSlot implements Serializable {  // ← Add this
    // ... rest of fields


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private LocalDateTime slotTime;
    private boolean booked;
    private Long candidateId; // filled when booked
}
