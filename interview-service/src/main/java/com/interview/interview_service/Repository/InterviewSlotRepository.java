package com.interview.interview_service.Repository;

import com.interview.interview_service.Entity.InterviewSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InterviewSlotRepository extends JpaRepository<InterviewSlot, Long> {
    List<InterviewSlot> findByBookedFalse();
}