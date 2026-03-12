package com.project.demo.Service.Impl;

import com.project.demo.DTOs.Requests.JobCreateDTO;
import com.project.demo.Exception.SalaryRangeException;
import com.project.demo.Service.HrJobService;
import org.springframework.stereotype.Service;

@Service
public class HrJobServiceImpl implements HrJobService {


    @Override
    public JobCreateDTO createJob(JobCreateDTO jobCreateDTO) {
        if(jobCreateDTO.getSalaryMin() > jobCreateDTO.getSalaryMax()) {
            throw new SalaryRangeException("Salary Min should be greater than Salary Max");
        }
        return jobCreateDTO;
    }
}
