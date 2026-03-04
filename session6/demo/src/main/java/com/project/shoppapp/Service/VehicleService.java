package com.project.shoppapp.Service;

import com.project.shoppapp.DTOs.Response.PageResponse;
import com.project.shoppapp.DTOs.Response.VehicleResponse;

public interface VehicleService {
    PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword);
}
