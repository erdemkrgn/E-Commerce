package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Bu sınıf REST API endpoint'lerini tanımlar.
@RequestMapping("/api/products") // Temel URL /api/products
public class ProductController {

    private final ProductService productService;

    // Constructor injection ile ProductService'i alıyoruz.
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET isteğiyle tüm ürünleri döndüren endpoint
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // POST isteğiyle yeni bir ürün ekleyen endpoint
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
