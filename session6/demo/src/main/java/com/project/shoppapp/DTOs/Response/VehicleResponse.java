package com.project.shoppapp.DTOs.Response;

import com.project.shoppapp.Entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}
