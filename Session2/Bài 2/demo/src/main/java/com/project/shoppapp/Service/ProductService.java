package com.project.shoppapp.Service;

import com.project.shoppapp.Models.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long id,Product product);
    void deleteProduct(Long id);
}
