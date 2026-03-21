package com.project.demo.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CandidateApplyDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    private MultipartFile file;
}
