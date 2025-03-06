package com.example.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity // Bu sınıfın bir JPA entity'si olduğunu belirtir.
public class Product {

    @Id // Bu alan, veritabanındaki primary key olacak.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID otomatik olarak oluşturulur.
    private Long id;

    private String name; // Ürün adı
    private String description; // Ürün açıklaması
    private BigDecimal price; // Ürün fiyatı

    // Getter ve Setter metodları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
