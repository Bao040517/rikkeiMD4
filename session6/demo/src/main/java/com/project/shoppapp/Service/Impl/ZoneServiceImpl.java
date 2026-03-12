package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.DTOs.Response.ZoneStatisticsResponse;
import com.project.shoppapp.Entity.Zone;
import com.project.shoppapp.Repository.ZoneRepository;
import com.project.shoppapp.Service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    ZoneRepository zoneRepository;


    @Override
    public List<ZoneStatisticsResponse> getZoneStatistics() {
        List<Zone> zones = zoneRepository.findAll();
        List<ZoneStatisticsResponse> rs = new ArrayList<>();
        for (Zone zone : zones) {
            ZoneStatisticsResponse zoneStatisticsResponse = new ZoneStatisticsResponse();
            zoneStatisticsResponse.setId(zone.getId());
            zoneStatisticsResponse.setCapacity(zone.getCapacity());
            zoneStatisticsResponse.setOccupiedSlots(zone.getOccupiedSpots());
            zoneStatisticsResponse.setAvailableSlots(zone.getCapacity() -  zone.getOccupiedSpots());
            rs.add(zoneStatisticsResponse);
        }
        return rs;
    }
}
