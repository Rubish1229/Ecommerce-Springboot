package com.rubish.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
    @GetMapping("{id}")
    public String openPayment(@PathVariable Long id, Model model){
        model.addAttribute("productId",id);
    return "payment/index";
    }
}
