package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Bu sınıf, servis katmanında iş mantığını barındırır.
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor ile dependency injection yapıyoruz.
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Tüm ürünleri döndüren metot
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Yeni bir ürün kaydetmek için metot
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
