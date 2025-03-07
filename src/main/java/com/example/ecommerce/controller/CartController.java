package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    // Kullanıcının sepetini görüntüler.
    @GetMapping
    public String viewCart(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            Cart cart = cartService.getCartByUser(user);
            model.addAttribute("cart", cart);
        }
        return "cart"; // resources/templates/cart.html
    }

    // Sepete ürün ekler.
    @PostMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Long productId,
                                   @RequestParam(defaultValue = "1") int quantity,
                                   Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                cartService.addProductToCart(user, product, quantity);
            }
        }
        return "redirect:/cart";
    }

    // Sepetten ürünü çıkarır.
    @PostMapping("/remove/{productId}")
    public String removeProductFromCart(@PathVariable Long productId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user != null) {
            cartService.removeProductFromCart(user, productId);
        }
        return "redirect:/cart";
    }
}
