package com.project.demo.Service;

import com.project.demo.DTOs.Requests.CandidateCreateDTO;
import com.project.demo.DTOs.Requests.CandidateUpdateDTO;
import com.project.demo.Entity.Candidate;

public interface CadidateService {
    Candidate createCandidate(CandidateCreateDTO candidateDTO);
    Candidate updateCadidate(Long id, CandidateUpdateDTO candidateUpdateDTO);
}
