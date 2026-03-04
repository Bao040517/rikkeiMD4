package com.project.shoppapp.Service;

import com.project.shoppapp.DTOs.Request.TicketRequest;
import com.project.shoppapp.DTOs.Response.TicketResponse;
import com.project.shoppapp.DTOs.Response.TicketSummaryResponse;

import java.util.List;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest req);
    TicketResponse checkOut(Long vehicleId);
    List<TicketSummaryResponse> getTodaySummary();
}
