package com.project.shoppapp.DTOs.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderCreateDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$")
    private String phoneNumber;

    @NotBlank
    private String address;

    private MultipartFile avatarFile;
}
