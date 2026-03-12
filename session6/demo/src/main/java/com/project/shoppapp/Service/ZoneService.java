package com.project.shoppapp.Service;

import com.project.shoppapp.DTOs.Response.ZoneStatisticsResponse;

import java.util.List;

public interface ZoneService {
    List<ZoneStatisticsResponse> getZoneStatistics();
}
