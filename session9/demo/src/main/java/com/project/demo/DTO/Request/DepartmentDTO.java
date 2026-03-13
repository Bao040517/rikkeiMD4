package com.project.demo.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @Size(max = 100, message = "Description tối đa 100 ký tự")
    private String description;
}
