package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Bu sınıf, Thymeleaf şablonlarını render etmek için kullanılır.
public class HomeController {

    // Ana sayfa endpoint'i: index.html şablonunu döndürür.
    @GetMapping({"/", "/index"})
    public String index() {
        return "index"; // resources/templates/index.html
    }

    // Giriş sayfası endpoint'i: login.html şablonunu döndürür.
    @GetMapping("/login")
    public String login() {
        return "login"; // resources/templates/login.html
    }
}
