package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.DTOs.Request.TicketRequest;
import com.project.shoppapp.DTOs.Response.TicketResponse;
import com.project.shoppapp.DTOs.Response.TicketSummaryResponse;
import com.project.shoppapp.Entity.ParkingTicket;
import com.project.shoppapp.Entity.Vehicle;
import com.project.shoppapp.Entity.Zone;
import com.project.shoppapp.Mappers.ParkingTicketMapper;
import com.project.shoppapp.Repository.ParkingTicketRepository;
import com.project.shoppapp.Repository.VehicleRepository;
import com.project.shoppapp.Repository.ZoneRepository;
import com.project.shoppapp.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    ParkingTicketRepository  parkingTicketRepository;

    @Autowired
    ParkingTicketMapper parkingTicketMapper;

    @Override
    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        Vehicle availableVehicle = vehicleRepository.findById(req.getVehicleId()).orElseThrow(() -> new IllegalArgumentException("Vehicle Not Found"));
        Zone zone = zoneRepository.findById(req.getZoneId()).orElseThrow(()-> new RuntimeException("Zone not found"));
        if(zone.getOccupiedSpots() >= zone.getCapacity()) throw new RuntimeException("Zone is occupied");
        ParkingTicket ticket = parkingTicketMapper.toEntity(req,availableVehicle,zone);
        parkingTicketRepository.save(ticket);
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        return parkingTicketMapper.toResponse(parkingTicketRepository.saveAndFlush(ticket));
    }

    @Override
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket ticket = parkingTicketRepository
                .findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found"));

        ticket.setCheckOutTime(LocalDateTime.now());

        ticket.getZone().setOccupiedSpots(ticket.getZone().getOccupiedSpots() - 1);

        return parkingTicketMapper.toResponse(ticket);
    }

    @Override
    public List<TicketSummaryResponse> getTodaySummary() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return parkingTicketRepository.findTodayTickets(start, end);
    }
}
