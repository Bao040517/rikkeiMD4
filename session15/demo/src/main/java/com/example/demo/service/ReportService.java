package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public BigDecimal calculateRevenue(String type) {
        List<Order> orders = orderRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        BigDecimal totalRevenue = BigDecimal.ZERO;

        for (Order order : orders) {
            // Thống kê những đơn hàng đã hoàn tất (DELIVERED) hoặc tuỳ theo quy trình.
            // Ở đây tạm tính tất cả đơn hoặc đơn không bị CANCELLED
            if ("CANCELLED".equals(order.getStatus())) continue;

            LocalDateTime createdDate = order.getCreatedDate();
            boolean match = false;

            if ("day".equalsIgnoreCase(type)) {
                match = (createdDate.toLocalDate().isEqual(now.toLocalDate()));
            } else if ("month".equalsIgnoreCase(type)) {
                match = (createdDate.getYear() == now.getYear() && createdDate.getMonth() == now.getMonth());
            } else if ("year".equalsIgnoreCase(type)) {
                match = (createdDate.getYear() == now.getYear());
            } else {
                // Mặc định tính tất cả nếu type không khớp
                match = true;
            }

            if (match && order.getTotalMoney() != null) {
                totalRevenue = totalRevenue.add(order.getTotalMoney());
            }
        }
        return totalRevenue;
    }
}
