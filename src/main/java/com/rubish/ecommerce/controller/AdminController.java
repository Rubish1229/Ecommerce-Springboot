package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private CustomerService customerService;

    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/orderLists")
    public String openListOfOrders(){
        return "sidebar/orderList";
    }

//    @GetMapping("/customerLists")
//    public String openCustomerLists(Model model){
//        model.addAttribute("customerList",customerService.getAllCustomers());
//        return "sidebar/customerList";
//    }
}
