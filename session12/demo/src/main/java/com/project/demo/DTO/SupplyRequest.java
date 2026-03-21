package com.project.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplyRequest {

    @NotBlank(message = "Tên vật tư không được để trống")
    private String name;

    private String specification;

    private String provider;

    private String unit;
}
