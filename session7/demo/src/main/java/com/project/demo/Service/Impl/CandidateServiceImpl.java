package com.project.demo.Service.Impl;

import com.project.demo.DTOs.Requests.CandidateCreateDTO;
import com.project.demo.DTOs.Requests.CandidateUpdateDTO;
import com.project.demo.Entity.Candidate;
import com.project.demo.Mappers.CandidateMapper;
import com.project.demo.Repository.CandidateRepository;
import com.project.demo.Service.CadidateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CadidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public Candidate createCandidate(CandidateCreateDTO candidateCreateDTO) {
        return candidateRepository.save(candidateMapper.toEntity(candidateCreateDTO));
    }
    @Transactional
    @Override
    public Candidate updateCadidate(Long id, CandidateUpdateDTO candidateUpdateDTO) {
        Candidate old = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("candidate not found"));
        old.setBio(candidateUpdateDTO.getBio());
        old.setAddress(candidateUpdateDTO.getAddress());
        return candidateRepository.save(old);
    }
}
