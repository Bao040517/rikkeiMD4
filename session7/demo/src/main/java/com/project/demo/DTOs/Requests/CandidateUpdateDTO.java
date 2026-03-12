package com.project.demo.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateDTO
{
    @NotBlank
    private String address;

    @NotBlank
    @Size(max = 200)
    private String bio;
}
