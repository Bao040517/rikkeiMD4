package com.project.demo.Controllers;

import com.project.demo.DTOs.Requests.JobCreateDTO;
import com.project.demo.DTOs.Responses.ApiResponse;
import com.project.demo.Service.HrJobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class HrNews {

    @Autowired
    HrJobService hrJobService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createJob(@Valid @RequestBody JobCreateDTO jobCreateDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "ok",
                        "Create job successfully",
                        hrJobService.createJob(jobCreateDTO)
                ));
    }
}
