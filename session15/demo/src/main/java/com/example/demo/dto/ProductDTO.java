package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    @NotBlank(message = "Tên không được để trống")
    private String name;
    private String description;

    @NotNull(message = "Giá không được để trống")
    private BigDecimal price;
    private String size;
    private String toppings;
}
