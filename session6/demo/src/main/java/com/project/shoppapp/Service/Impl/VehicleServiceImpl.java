package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.DTOs.Response.PageResponse;
import com.project.shoppapp.DTOs.Response.VehicleResponse;
import com.project.shoppapp.Repository.VehicleRepository;
import com.project.shoppapp.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(
            int page,
            int size,
            String sortBy,
            String direction,
            String keyword
    ) {
        if (page < 0) page = 0;
        if (size <= 0) size = 5;
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        if (direction == null || direction.isBlank()) {
            direction = "asc";
        }
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<VehicleResponse> result = vehicleRepository.findAllByKeyword(keyword, pageable);
        return PageResponse.<VehicleResponse>builder()
                .items(result.getContent())
                .page(result.getNumber())
                .size(result.getSize())
                .totalItems(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .isLast(result.isLast())
                .build();
    }
}
