package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.ProductDto;
import com.rubish.ecommerce.model.Category;
import com.rubish.ecommerce.model.Product;
import com.rubish.ecommerce.repository.CategoryRepo;
import com.rubish.ecommerce.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product saveProduct(ProductDto productDto, MultipartFile file)throws Exception{
        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        String uploadDir="uploads/";

        File folder=new File(uploadDir);
        if(!folder.exists()){
            folder.mkdirs();
        }

        File destination = new File(uploadDir+fileName);
        file.transferTo(destination);

        Category category=categoryRepo.findById(productDto.getCategoryId())
                .orElseThrow(()->new RuntimeException("category not found"));

        Product product=new Product();
        product.setImageUrl("/uploads/" + fileName);
        product.setProductName(productDto.getProductName());
        product.setTagName(productDto.getTagName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(category);


        return productRepo.save(product);
    }
}
