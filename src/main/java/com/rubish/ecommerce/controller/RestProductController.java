package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.dto.ProductDto;
import com.rubish.ecommerce.model.Product;
import com.rubish.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class RestProductController {

    private ProductService productService;

    public RestProductController(ProductService productService) {
        this.productService = productService;
    }




    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto productDto,
                                           @RequestPart("image")MultipartFile file){
   try {
       ProductDto savedProduct = productService.saveProduct(productDto, file);
       return ResponseEntity.ok(savedProduct);
   }catch (Exception e){
       e.printStackTrace();
       return ResponseEntity.status(500).body("Error saving product");
   }

    }


}
