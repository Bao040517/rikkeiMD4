package com.project.demo.Service;

import com.project.demo.DTOs.Requests.JobCreateDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface HrJobService {
    JobCreateDTO createJob(@Valid @RequestBody JobCreateDTO jobCreateDTO);
}
