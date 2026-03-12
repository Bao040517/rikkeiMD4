package com.project.demo.DTOs.Requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCreateDTO {

    @NotBlank
    private String title;

    @Valid
    @NotNull
    CompanyDTO company;

    @Min(0)
    private Long salaryMin;

    @Min(0)
    private Long salaryMax;

}
