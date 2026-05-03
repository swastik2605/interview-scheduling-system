package com.interview.Interview_Service.dto;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class InterviewDTO implements Serializable {

    private String CandidateId;
    private String InterviewerId;
    private String time;
}
