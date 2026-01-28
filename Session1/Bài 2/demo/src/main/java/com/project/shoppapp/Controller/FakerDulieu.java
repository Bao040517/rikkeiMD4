package com.project.shoppapp.Controller;

import com.github.javafaker.Faker;
import com.project.shoppapp.Models.Product;
import com.project.shoppapp.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class FakerDulieu {

    private final ProductRepository productRepository;

    @GetMapping("/seed-products")
    public String seedProducts() {
        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {
            Product p = new Product();
            p.setName(faker.commerce().productName());
            p.setPrice(faker.commerce().price());

            productRepository.save(p);
        }

        return "Đã seed 20 products!";
    }
}
