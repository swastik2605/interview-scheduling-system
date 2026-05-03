package com.interview.Interview_Service.controller;

import com.interview.Interview_Service.Repository.InterviewRepo;
import com.interview.Interview_Service.dto.InterviewDTO;
import com.interview.Interview_Service.model.Interview;
import com.interview.Interview_Service.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping("/book")
    public Interview book(@RequestBody InterviewDTO dto) {
        return interviewService.BookInterview(dto);

    }
    @GetMapping("/{id}")
    public InterviewDTO get(@PathVariable Long id) {
        return interviewService.getInterview(id);
    }
    @GetMapping("/all")
    public List<InterviewDTO> getInterviews() {
       return interviewService.getallinterviews();


    }
}

