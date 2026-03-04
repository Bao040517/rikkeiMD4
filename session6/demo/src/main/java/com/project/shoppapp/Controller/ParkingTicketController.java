package com.project.shoppapp.Controller;

import com.project.shoppapp.DTOs.Request.TicketRequest;
import com.project.shoppapp.DTOs.Response.ApiResponse;
import com.project.shoppapp.DTOs.Response.TicketResponse;
import com.project.shoppapp.Service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class ParkingTicketController {

    private final ParkingService parkingService;

    @PostMapping("/check-in")
    public ApiResponse<TicketResponse> checkIn(
            @RequestBody @Valid TicketRequest request
    ) {
        TicketResponse response = parkingService.checkIn(request);

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Vehicle checked in successfully")
                .data(response)
                .build();
    }

    @PutMapping("/check-out/{vehicleId}")
    public ApiResponse<TicketResponse> checkOut(
            @PathVariable Long vehicleId
    ) {

        TicketResponse response = parkingService.checkOut(vehicleId);

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Vehicle checked out successfully")
                .data(response)
                .build();
    }
}
