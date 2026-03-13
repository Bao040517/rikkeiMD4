package com.project.demo.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeCreateDTO {
    @Email
    private String email;
    @Pattern(regexp = "^(03|05|07|08|09)[0-9]{8}$")
    private String phone;
    @Min(5000000)
    private Double salary;
    @NotNull
    private Long department_id;
}
