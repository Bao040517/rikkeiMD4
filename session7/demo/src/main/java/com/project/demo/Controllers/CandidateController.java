package com.project.demo.Controllers;

import com.project.demo.DTOs.Requests.CandidateCreateDTO;
import com.project.demo.DTOs.Requests.CandidateUpdateDTO;
import com.project.demo.DTOs.Responses.ApiResponse;
import com.project.demo.Entity.Candidate;
import com.project.demo.Service.CadidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    CadidateService candidateService;


    @PostMapping()
    public ResponseEntity<ApiResponse<?>> candidate(@Valid @RequestBody CandidateCreateDTO candidateCreateDTO) {
        return ResponseEntity.status(201).body(
                new ApiResponse<>("success",
                        "Create candidate successfully",
                        candidateService.createCandidate(candidateCreateDTO)));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<?>> updateCandidate(@Valid @ModelAttribute CandidateUpdateDTO candidateUpdateDTO, @PathVariable Long id) {
        return ResponseEntity.status(202).body(
                new ApiResponse<>(
                        "success",
                        "update complete",
                        candidateService.updateCadidate(id, candidateUpdateDTO)                )
        );
    }
}
