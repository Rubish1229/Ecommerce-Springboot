package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.service.CategoryService;
import com.rubish.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "sidebar/addProduct";
    }

    @GetMapping("/fetchAllProducts")
    public String getAllProduct(Model model){
        model.addAttribute("productList",productService.getAllProduct());
        return "customer/products";
    }
}


