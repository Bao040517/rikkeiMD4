package com.project.shoppapp.DTOs.Request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateStockDTO {
    @Min(value = 0)
    private Integer stock;
}
