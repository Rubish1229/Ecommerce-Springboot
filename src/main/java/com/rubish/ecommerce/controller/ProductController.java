package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.dto.ProductDto;
import com.rubish.ecommerce.model.Product;
import com.rubish.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createProduct(@RequestPart("product")ProductDto productDto,
                                           @RequestPart("image")MultipartFile file){
   try {
       Product savedProduct = productService.saveProduct(productDto, file);
       return ResponseEntity.ok(savedProduct);
   }catch (Exception e){
        return ResponseEntity.status(500).body("Error saving product");
   }

    }


}
