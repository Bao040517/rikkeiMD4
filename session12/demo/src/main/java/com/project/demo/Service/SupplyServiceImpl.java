package com.project.demo.Service;

import com.project.demo.DTO.*;
import com.project.demo.Entity.Supply;
import com.project.demo.Entity.Transaction;
import com.project.demo.Entity.TransactionType;
import com.project.demo.Exception.BadRequestException;
import com.project.demo.Exception.InsufficientStockException;
import com.project.demo.Exception.ResourceNotFoundException;
import com.project.demo.Repository.SupplyRepository;
import com.project.demo.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final TransactionRepository transactionRepository;

    // Logger riêng ghi vào history.log
    private static final Logger historyLogger = LoggerFactory.getLogger("HISTORY_LOGGER");

    // ========== Chức năng 1: Thêm mới vật tư ==========
    @Override
    @Transactional
    public Supply createSupply(SupplyRequest request) {
        Supply supply = Supply.builder()
                .name(request.getName())
                .specification(request.getSpecification())
                .provider(request.getProvider())
                .unit(request.getUnit())
                .quantity(0) // Luôn mặc định = 0
                .isDeleted(false)
                .build();

        Supply saved = supplyRepository.save(supply);
        log.info("Đã tạo mới vật tư: {} với ID: {}", saved.getName(), saved.getId());
        return saved;
    }

    // ========== Chức năng 2: Cập nhật thông tin vật tư ==========
    @Override
    @Transactional
    public Supply updateSupply(Long id, SupplyUpdateRequest request) {
        // Kiểm tra trường cấm
        if (request.getId() != null) {
            log.warn("Client cố tình gửi trường cấm 'id' trong request body khi cập nhật vật tư ID: {}", id);
            throw new BadRequestException("Không được phép gửi trường 'id' trong body request");
        }
        if (request.getQuantity() != null) {
            log.warn("Client cố tình gửi trường cấm 'quantity' trong request body khi cập nhật vật tư ID: {}", id);
            throw new BadRequestException("Không được phép gửi trường 'quantity' trong body request");
        }

        Supply supply = supplyRepository.findById(id)
                .filter(s -> !s.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vật tư với ID: " + id));

        if (request.getName() != null) {
            supply.setName(request.getName());
        }
        if (request.getSpecification() != null) {
            supply.setSpecification(request.getSpecification());
        }
        if (request.getProvider() != null) {
            supply.setProvider(request.getProvider());
        }

        Supply updated = supplyRepository.save(supply);
        log.info("Đã cập nhật vật tư ID: {}, tên: {}", updated.getId(), updated.getName());
        return updated;
    }

    // ========== Chức năng 3: Xóa vật tư (Xóa mềm) ==========
    @Override
    @Transactional
    public void deleteSupply(Long id) {
        Supply supply = supplyRepository.findById(id)
                .filter(s -> !s.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vật tư với ID: " + id + " hoặc vật tư đã bị xóa trước đó"));

        supply.setIsDeleted(true);
        supplyRepository.save(supply);
        log.info("Đã xóa mềm vật tư ID: {}, tên: {}", id, supply.getName());
    }

    // ========== Chức năng 4: Hiển thị danh sách vật tư ==========
    @Override
    public List<Supply> getAllActiveSupplies() {
        List<Supply> supplies = supplyRepository.findAllByIsDeletedFalse();
        log.debug("Truy vấn danh sách vật tư, số lượng bản ghi: {}", supplies.size());
        return supplies;
    }

    // ========== Chức năng 5: Tìm kiếm vật tư theo tên ==========
    @Override
    public List<Supply> searchSuppliesByName(String name) {
        List<Supply> supplies = supplyRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name);
        if (supplies.isEmpty()) {
            log.info("Không tìm thấy vật tư nào khớp với từ khóa: {}", name);
        } else {
            log.info("Tìm thấy {} vật tư khớp với từ khóa: {}", supplies.size(), name);
        }
        return supplies;
    }

    // ========== Chức năng 6: Xuất vật tư (Xuất kho) ==========
    @Override
    @Transactional
    public Supply exportSupply(Long id, AmountRequest request) {
        Supply supply = supplyRepository.findById(id)
                .filter(s -> !s.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vật tư với ID: " + id));

        int currentStock = supply.getQuantity();
        int exportAmount = request.getAmount();

        if (currentStock < exportAmount) {
            log.error("Thất bại khi xuất kho ID {}: Yêu cầu {}, hiện có {}", id, exportAmount, currentStock);
            throw new InsufficientStockException("Số lượng tồn kho không đủ để xuất");
        }

        int newStock = currentStock - exportAmount;
        supply.setQuantity(newStock);
        supplyRepository.save(supply);

        // Lưu giao dịch
        Transaction transaction = Transaction.builder()
                .supply(supply)
                .type(TransactionType.EXPORT)
                .amount(exportAmount)
                .quantityBefore(currentStock)
                .quantityAfter(newStock)
                .build();
        transactionRepository.save(transaction);

        log.info("Xuất kho thành công ID {}: số lượng -{}, tồn cũ {}, tồn mới {}",
                id, exportAmount, currentStock, newStock);
        return supply;
    }

    // ========== Chức năng 7: Nhập vật tư (Nhập kho) ==========
    @Override
    @Transactional
    public Supply importSupply(Long id, AmountRequest request) {
        Supply supply = supplyRepository.findById(id)
                .filter(s -> !s.getIsDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vật tư với ID: " + id));

        int currentStock = supply.getQuantity();
        int importAmount = request.getAmount();
        int newStock = currentStock + importAmount;

        supply.setQuantity(newStock);
        supplyRepository.save(supply);

        // Lưu giao dịch
        Transaction transaction = Transaction.builder()
                .supply(supply)
                .type(TransactionType.IMPORT)
                .amount(importAmount)
                .quantityBefore(currentStock)
                .quantityAfter(newStock)
                .build();
        transactionRepository.save(transaction);

        // Ghi log vào file riêng history.log
        historyLogger.info("Nhập kho ID {}, số lượng +{}, tồn cũ {}", id, importAmount, currentStock);

        log.info("Nhập kho thành công ID {}: số lượng +{}, tồn cũ {}, tồn mới {}",
                id, importAmount, currentStock, newStock);
        return supply;
    }

    // ========== Chức năng 8: Thống kê xuất kho trong ngày ==========
    @Override
    public List<DailyExportDTO> getDailyExportStatistics() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        log.info("Bắt đầu thống kê xuất kho trong ngày: {}", LocalDate.now());
        long startTime = System.currentTimeMillis();

        List<Object[]> results = transactionRepository.findDailyExportStatistics(
                TransactionType.EXPORT, startOfDay, endOfDay);

        List<DailyExportDTO> statistics = results.stream()
                .map(row -> DailyExportDTO.builder()
                        .supplyId((Long) row[0])
                        .supplyName((String) row[1])
                        .totalExportQuantity((Long) row[2])
                        .build())
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        log.info("Hoàn thành thống kê xuất kho trong ngày. Thời gian xử lý: {} ms", (endTime - startTime));

        return statistics;
    }

    // ========== Chức năng 9: Top vật tư xuất kho nhiều nhất ==========
    @Override
    public TopExportDTO getTopExportSupply() {
        if (!transactionRepository.existsByType(TransactionType.EXPORT)) {
            throw new ResourceNotFoundException("Chưa có dữ liệu giao dịch để thống kê");
        }

        List<Object[]> results = transactionRepository.findTopExportSupplies(TransactionType.EXPORT);

        if (results.isEmpty()) {
            throw new ResourceNotFoundException("Chưa có dữ liệu giao dịch để thống kê");
        }

        Object[] topResult = results.get(0);
        TopExportDTO topExport = TopExportDTO.builder()
                .topSupplyId((Long) topResult[0])
                .topSupplyName((String) topResult[1])
                .totalExportQuantity((Long) topResult[2])
                .build();

        log.info("Top vật tư xuất kho nhiều nhất: {} (ID: {}), tổng xuất: {}",
                topExport.getTopSupplyName(), topExport.getTopSupplyId(), topExport.getTotalExportQuantity());

        return topExport;
    }
}
