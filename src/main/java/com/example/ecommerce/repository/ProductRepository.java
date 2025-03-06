package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA kullanılarak Product entity'sine ait CRUD işlemlerini gerçekleştiren repository.
public interface ProductRepository extends JpaRepository<Product, Long> {
    // İleride özel sorgular eklemek isterseniz burada tanımlayabilirsiniz.
}
