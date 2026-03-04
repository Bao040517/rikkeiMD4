package com.project.demo.controllers;

import com.project.demo.dtos.InstructorCreateRequestDTO;
import com.project.demo.models.Instructor;
import com.project.demo.reponses.ApiResponse;
import com.project.demo.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createInstructor(
            @RequestBody InstructorCreateRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .message("Instructor has been successfully created")
                        .code("200")
                        .data(null)
                .build());
    }
}