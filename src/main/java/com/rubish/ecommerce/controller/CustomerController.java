package com.rubish.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class CustomerController {
    @GetMapping
    public String showSignupPage(){
        return "signuplogin/home"; // your HTML file name
    }
}
