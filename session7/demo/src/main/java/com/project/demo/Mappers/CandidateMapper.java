package com.project.demo.Mappers;

import com.project.demo.DTOs.Requests.CandidateCreateDTO;
import com.project.demo.Entity.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {
    public Candidate toEntity(CandidateCreateDTO candidateCreateDTO) {
        Candidate candidate = new Candidate();
        candidate.setAge(candidateCreateDTO.getAge());
        candidate.setEmail(candidateCreateDTO.getEmail());
        candidate.setFullname(candidateCreateDTO.getFullName());
        candidate.setYearsOfExperience(candidateCreateDTO.getYearsOfExperience());
        return candidate;
    }
}
