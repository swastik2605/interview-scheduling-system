package com.interview.candidate_service.service;

import com.interview.candidate_service.dto.CandidateDTO;
import com.interview.candidate_service.model.Candidate;
import com.interview.candidate_service.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository repo;

    public CandidateDTO register(CandidateDTO dto) {
        if (repo.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email already registered: " + dto.getEmail());

        Candidate candidate = Candidate.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .skills(dto.getSkills())
                .status("REGISTERED")
                .build();

        Candidate saved = repo.save(candidate);
        return toDTO(saved);
    }

    public CandidateDTO getById(Long id) {
        Candidate c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + id));
        return toDTO(c);
    }

    public List<CandidateDTO> getAll() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CandidateDTO update(Long id, CandidateDTO dto) {
        Candidate c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + id));
        c.setName(dto.getName());
        c.setPhone(dto.getPhone());
        c.setSkills(dto.getSkills());
        return toDTO(repo.save(c));
    }

    private CandidateDTO toDTO(Candidate c) {
        return CandidateDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .email(c.getEmail())
                .phone(c.getPhone())
                .skills(c.getSkills())
                .status(c.getStatus())
                .build();
    }
}