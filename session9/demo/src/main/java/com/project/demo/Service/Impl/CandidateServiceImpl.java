package com.project.demo.Service.Impl;

import com.project.demo.DTO.Request.CandidateApplyDTO;
import com.project.demo.Entity.Candidate;
import com.project.demo.Service.CandidateService;
import jakarta.transaction.Transactional;

public class CandidateServiceImpl implements CandidateService {

    @Transactional
    @Override
    public Candidate createCandidate(CandidateApplyDTO candidateApplyDTO) {
        return null;
    }
}
