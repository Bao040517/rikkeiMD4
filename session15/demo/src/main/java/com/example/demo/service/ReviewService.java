package com.example.demo.service;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Review addReview(String email, ReviewRequestDTO requestDTO) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra xem user đã mua sản phẩm này chưa
        boolean hasBought = false;
        List<Order> orders = orderRepository.findByUserId(user.getId());
        for (Order order : orders) {
            // Có thể thêm điều kiện status order là DELIVERED/hoàn thành
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
            for (OrderItem item : items) {
                if (item.getProduct().getId().equals(product.getId())) {
                    hasBought = true;
                    break;
                }
            }
            if (hasBought) break;
        }

        if (!hasBought) {
            throw new RuntimeException("You can only review products you have purchased.");
        }

        Review review = Review.builder()
                .user(user)
                .product(product)
                .rating(requestDTO.getRating())
                .comment(requestDTO.getComment())
                .createdDate(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}
