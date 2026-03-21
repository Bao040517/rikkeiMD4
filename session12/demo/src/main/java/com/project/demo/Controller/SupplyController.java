package com.project.demo.Controller;

import com.project.demo.DTO.*;
import com.project.demo.Entity.Supply;
import com.project.demo.Service.SupplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/supplies")
@RequiredArgsConstructor
public class SupplyController {

    private final SupplyService supplyService;

    // ========== Chức năng 1: Thêm mới vật tư ==========
    @PostMapping
    public ResponseEntity<ApiResponse<Supply>> createSupply(@Valid @RequestBody SupplyRequest request) {
        Supply supply = supplyService.createSupply(request);
        ApiResponse<Supply> response = ApiResponse.success(
                HttpStatus.CREATED.value(),
                "Tạo mới vật tư thành công",
                supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ========== Chức năng 2: Cập nhật thông tin vật tư ==========
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supply>> updateSupply(
            @PathVariable Long id,
            @RequestBody SupplyUpdateRequest request) {
        Supply supply = supplyService.updateSupply(id, request);
        ApiResponse<Supply> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Cập nhật vật tư thành công",
                supply);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 3: Xóa vật tư (Xóa mềm) ==========
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
        supplyService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }

    // ========== Chức năng 4: Hiển thị danh sách vật tư ==========
    @GetMapping
    public ResponseEntity<ApiResponse<List<Supply>>> getAllSupplies() {
        List<Supply> supplies = supplyService.getAllActiveSupplies();
        ApiResponse<List<Supply>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Lấy danh sách vật tư thành công",
                supplies);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 5: Tìm kiếm vật tư theo tên ==========
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Supply>>> searchSupplies(@RequestParam("name") String name) {
        List<Supply> supplies = supplyService.searchSuppliesByName(name);
        ApiResponse<List<Supply>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Tìm kiếm vật tư thành công",
                supplies);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 6: Xuất vật tư (Xuất kho) ==========
    @PatchMapping("/{id}/export")
    public ResponseEntity<ApiResponse<Supply>> exportSupply(
            @PathVariable Long id,
            @Valid @RequestBody AmountRequest request) {
        Supply supply = supplyService.exportSupply(id, request);
        ApiResponse<Supply> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Xuất kho thành công",
                supply);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 7: Nhập vật tư (Nhập kho) ==========
    @PatchMapping("/{id}/import")
    public ResponseEntity<ApiResponse<Supply>> importSupply(
            @PathVariable Long id,
            @Valid @RequestBody AmountRequest request) {
        Supply supply = supplyService.importSupply(id, request);
        ApiResponse<Supply> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Nhập kho thành công",
                supply);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 8: Thống kê xuất kho trong ngày ==========
    @GetMapping("/statistics/daily-export")
    public ResponseEntity<ApiResponse<List<DailyExportDTO>>> getDailyExportStatistics() {
        List<DailyExportDTO> statistics = supplyService.getDailyExportStatistics();
        ApiResponse<List<DailyExportDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Thống kê xuất kho trong ngày thành công",
                statistics);
        return ResponseEntity.ok(response);
    }

    // ========== Chức năng 9: Top vật tư xuất kho nhiều nhất ==========
    @GetMapping("/statistics/top-export")
    public ResponseEntity<ApiResponse<TopExportDTO>> getTopExportSupply() {
        TopExportDTO topExport = supplyService.getTopExportSupply();
        ApiResponse<TopExportDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "Thống kê vật tư xuất kho nhiều nhất thành công",
                topExport);
        return ResponseEntity.ok(response);
    }
}
