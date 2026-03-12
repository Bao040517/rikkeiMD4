package com.project.shoppapp.DTOs.Request;

import com.project.shoppapp.Validation.ExistingBookId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BorrowCreateDTO {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @ExistingBookId
    private Long bookId;
}