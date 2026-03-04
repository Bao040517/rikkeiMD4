package com.project.shoppapp.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSummaryResponse {
    Long id;
    String licensePlate;
    String zoneName;
    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
}
