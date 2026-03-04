package com.project.shoppapp.Mappers;

import com.project.shoppapp.DTOs.Request.TicketRequest;
import com.project.shoppapp.DTOs.Response.TicketResponse;
import com.project.shoppapp.Entity.ParkingTicket;
import com.project.shoppapp.Entity.Vehicle;
import com.project.shoppapp.Entity.Zone;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ParkingTicketMapper {

    public ParkingTicket toEntity(
            TicketRequest req,
            Vehicle vehicle,
            Zone zone
    ) {
        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now());
        return ticket;
    }

    public TicketResponse toResponse(ParkingTicket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .checkInTime(ticket.getCheckInTime())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .zoneName(ticket.getZone().getName())
                .build();
    }
}