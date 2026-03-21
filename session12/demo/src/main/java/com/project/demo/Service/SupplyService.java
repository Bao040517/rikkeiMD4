package com.project.demo.Service;

import com.project.demo.DTO.*;
import com.project.demo.Entity.Supply;

import java.util.List;

public interface SupplyService {

    // Chức năng 1: Thêm mới vật tư
    Supply createSupply(SupplyRequest request);

    // Chức năng 2: Cập nhật thông tin vật tư
    Supply updateSupply(Long id, SupplyUpdateRequest request);

    // Chức năng 3: Xóa vật tư (xóa mềm)
    void deleteSupply(Long id);

    // Chức năng 4: Hiển thị danh sách vật tư hiện có
    List<Supply> getAllActiveSupplies();

    // Chức năng 5: Tìm kiếm vật tư theo tên
    List<Supply> searchSuppliesByName(String name);

    // Chức năng 6: Xuất vật tư (xuất kho)
    Supply exportSupply(Long id, AmountRequest request);

    // Chức năng 7: Nhập vật tư (nhập kho)
    Supply importSupply(Long id, AmountRequest request);

    // Chức năng 8: Thống kê vật tư xuất kho trong ngày
    List<DailyExportDTO> getDailyExportStatistics();

    // Chức năng 9: Thống kê vật tư xuất kho nhiều nhất
    TopExportDTO getTopExportSupply();
}
