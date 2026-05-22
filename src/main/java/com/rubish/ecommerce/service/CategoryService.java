package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.CategoryDto;
import com.rubish.ecommerce.model.Category;
import com.rubish.ecommerce.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CategoryDto> getAllCategories(){
        List<Category> categories=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for(Category category:categories){
            CategoryDto categoryDto=new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDtos.add(categoryDto);
        }
            return categoryDtos;
    }
}
