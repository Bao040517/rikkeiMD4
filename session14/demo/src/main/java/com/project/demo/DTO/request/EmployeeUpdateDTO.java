package com.project.demo.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmployeeUpdateDTO {
    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 5, message = "Họ tên phải có ít nhất 5 ký tự")
    private String fullName;

    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Phòng ban không được để trống")
    private String department;

    private MultipartFile avatarFile;
}
