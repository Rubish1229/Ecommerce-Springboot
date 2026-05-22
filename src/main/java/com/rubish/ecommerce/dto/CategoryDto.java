package com.rubish.ecommerce.dto;

import com.rubish.ecommerce.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;

    private String categoryName;


}
