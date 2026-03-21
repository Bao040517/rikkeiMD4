package com.example.demo.controller;

import com.example.demo.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getRevenueReport(@RequestParam(defaultValue = "month") String type) {
        BigDecimal revenue = reportService.calculateRevenue(type);
        Map<String, Object> response = new HashMap<>();
        response.put("type", type);
        response.put("revenue", revenue);
        return ResponseEntity.ok(response);
    }
}
