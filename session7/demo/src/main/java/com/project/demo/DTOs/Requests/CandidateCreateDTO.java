package com.project.demo.DTOs.Requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateCreateDTO {

    @NotBlank
    @Size(min = 5, max = 50)
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Positive
    @Min(18)
    private Integer age;

    @PositiveOrZero
    private Integer yearsOfExperience;

    @NotBlank
    private String address;

    @NotBlank
    private String bio;

    @Pattern(regexp = "^0[35789][0-9]{8}$", message = "Số điện thoại không hợp lệ")
    private String phone;
}
