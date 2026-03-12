package com.project.shoppapp.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneStatisticsResponse {
    private Long id;
    private Long capacity;
    private Long occupiedSlots;
    private Long availableSlots;

}
