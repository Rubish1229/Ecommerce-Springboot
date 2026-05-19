package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.CategoryDto;
import com.rubish.ecommerce.model.Category;
import com.rubish.ecommerce.repository.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepo  categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category addCategory(CategoryDto categoryDto){
        Category category=new Category();
        category.setCategoryName(categoryDto.getCategoryName());

        return categoryRepo.save(category);
    }
}
