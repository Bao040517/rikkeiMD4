package com.project.shoppapp.Controller;

import com.project.shoppapp.DTOs.Response.ApiResponse;
import com.project.shoppapp.DTOs.Response.PageResponse;
import com.project.shoppapp.DTOs.Response.VehicleResponse;
import com.project.shoppapp.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ApiResponse<PageResponse<VehicleResponse>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword
    ) {

        PageResponse<VehicleResponse> result = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);

        return ApiResponse.<PageResponse<VehicleResponse>>builder()
                .success(true)
                .message("Lấy danh sách xe thành công")
                .data(result)
                .build();
    }

}
