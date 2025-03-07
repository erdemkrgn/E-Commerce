package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Kayıt formunu gösterir.
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    // Kayıt işlemini gerçekleştirir.
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, Model model){
        try {
            userService.registerNewUser(user);
        } catch(Exception e) {
            model.addAttribute("registrationError", e.getMessage());
            return "register";
        }
        return "redirect:/login?registerSuccess";
    }
}
