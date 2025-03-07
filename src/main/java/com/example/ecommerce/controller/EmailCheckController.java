package com.example.ecommerce.controller;

import com.example.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/check-email")
public class EmailCheckController {

    private final UserService userService;

    public EmailCheckController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailTaken(email);
        return Collections.singletonMap("exists", exists);
    }
}
