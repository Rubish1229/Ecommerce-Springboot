package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.dto.CustomerDto;
import com.rubish.ecommerce.model.Customer;
import com.rubish.ecommerce.repository.CustomerRepo;
import com.rubish.ecommerce.service.CustomerService;
import com.rubish.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private final ProductService productService;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo, ProductService productService) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.productService = productService;
    }

    @PostMapping("/signup")
    public String customerSignupPage(@ModelAttribute CustomerDto customerDto){
        customerService.createCustomer(customerDto);
        return "signuplogin/customerLogin";
    }

    @PostMapping("/login")
    public String customerLogin(@RequestParam String email, @RequestParam String password, Model model){
       CustomerDto customerDto= customerService.findByEmail(email);

       if(customerDto!=null && customerDto.getPassword().equals(password)){
           return "redirect:/customer/HomePage";
       }else {
           model.addAttribute("error","Invalid email and password");
           return "signupLogin/customerLogin";
       }
        }

    @GetMapping("/HomePage")
    public String getAllProduct(Model model){

        model.addAttribute("productList",productService.getAllProduct());
        return "customer/customerHomePage";
    }

    @GetMapping("/productDetail/{id}")
    public String getProductById(@PathVariable Long id,Model model){
        productService.getProductById(id);
        return "customer/productdetail";
    }

}
