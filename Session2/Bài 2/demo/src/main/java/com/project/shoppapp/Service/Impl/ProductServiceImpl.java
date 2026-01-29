package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.Models.Product;
import com.project.shoppapp.Repository.ProductRepository;
import com.project.shoppapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if(productRepository.findById(id).isPresent()){
            Product product1 = productRepository.findById(id).get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            return productRepository.save(product1);
        }
        System.out.println("Không có sản phẩm này");
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
        }
        else{
            System.out.println("Không tồn tại sản phẩm này");
        }
    }
}
