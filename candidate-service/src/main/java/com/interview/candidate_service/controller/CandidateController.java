package com.interview.candidate_service.controller;

import com.interview.candidate_service.dto.CandidateDTO;
import com.interview.candidate_service.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @PostMapping("/register")
    public ResponseEntity<CandidateDTO> register(@Valid @RequestBody CandidateDTO dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDTO> update(@PathVariable Long id,
                                               @Valid @RequestBody CandidateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}