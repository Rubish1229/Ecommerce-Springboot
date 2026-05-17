package com.rubish.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navbar")
public class NavController {
    @GetMapping("signup")
    public String showSignupPage(){
        return "signupLogin/customerSignup"; // your HTML file name
    }

    @GetMapping("login")
    public String showLoginPage(){
        return "signupLogin/customerLogin"; // your HTML file name
    }
}
