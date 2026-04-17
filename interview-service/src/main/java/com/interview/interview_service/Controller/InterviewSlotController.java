package com.interview.interview_service.Controller;


import com.interview.interview_service.Entity.InterviewSlot;
import com.interview.interview_service.Service.InterviewSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class InterviewSlotController {

    private final InterviewSlotService slotService;

    // HR adds a slot
    @PostMapping
    public ResponseEntity<InterviewSlot> addSlot(@RequestBody InterviewSlot slot) {
        return ResponseEntity.ok(slotService.addSlot(slot));
    }

    // Get all available slots (Redis cached)
    @GetMapping
    public ResponseEntity<List<InterviewSlot>> getAvailableSlots() {
        return ResponseEntity.ok(slotService.getAvailableSlots());
    }

    // Candidate books a slot
    @PutMapping("/book/{slotId}")
    public ResponseEntity<InterviewSlot> bookSlot(
            @PathVariable Long slotId,
            @RequestParam Long candidateId) {
        return ResponseEntity.ok(slotService.bookSlot(slotId, candidateId));
    }
}
