package com.rubish.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String imageUrl;
    private String productName;
    private String tagName;
    private String productDescription;
    private BigDecimal productPrice;
    private int stockQuantity;
    private String productSize;


    private Long categoryId;
    private String categoryName;

}
