package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.dto.CategoryDto;
import com.rubish.ecommerce.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String openCategory(){
        return "sidebar/addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return "redirect:/category";
    }
}
