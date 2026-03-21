package com.project.demo.Service;

import com.project.demo.DTO.Request.CandidateApplyDTO;
import com.project.demo.Entity.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateApplyDTO candidateApplyDTO);
}
