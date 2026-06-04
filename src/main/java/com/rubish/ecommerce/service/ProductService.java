package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.CategoryDto;
import com.rubish.ecommerce.dto.ProductDto;
import com.rubish.ecommerce.model.Category;
import com.rubish.ecommerce.model.Product;
import com.rubish.ecommerce.repository.CategoryRepo;
import com.rubish.ecommerce.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public ProductDto saveProduct(ProductDto productDto, MultipartFile file)throws Exception{
        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File destination = new File(uploadDir + fileName);
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
        product.setProductSize(productDto.getProductSize());
        product.setCategory(category);


        Product savedProduct= productRepo.save(product);

        ProductDto productDto1=new ProductDto();
        productDto1.setImageUrl(product.getImageUrl());
        productDto1.setProductName(product.getProductName());
        productDto1.setTagName(product.getTagName());
        productDto1.setProductDescription(product.getProductDescription());
        productDto1.setProductPrice(product.getProductPrice());
        productDto1.setStockQuantity(product.getStockQuantity());
        productDto1.setProductSize(productDto.getProductSize());
        productDto1.setCategoryId(productDto.getCategoryId());
        productDto1.setCategoryName(productDto.getCategoryName());

        return productDto1;
    }


    public List<ProductDto> getAllProduct(){
        return productRepo.findAll().stream().map(product -> {
                            ProductDto productDto=new ProductDto();
                            productDto.setProductId(product.getProductId());
                            productDto.setImageUrl(product.getImageUrl());
                            productDto.setProductName(product.getProductName());
                            productDto.setTagName(product.getTagName());
                            productDto.setProductDescription(product.getProductDescription());
                            productDto.setProductPrice(product.getProductPrice());
                            productDto.setStockQuantity(product.getStockQuantity());
                            productDto.setProductSize(product.getProductSize());

                            productDto.setCategoryId(product.getCategory().getCategoryId());
                            productDto.setCategoryName(product.getCategory().getCategoryName());

                            return productDto;
        }).toList();
    }

                    public ProductDto getProductById(Long id) {
                       Product product=productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found!"));

                       ProductDto productDto=new ProductDto();
                       productDto.setProductId(product.getProductId());
                       productDto.setProductName(product.getProductName());
                       productDto.setProductPrice(product.getProductPrice());
                       productDto.setProductDescription(product.getProductDescription());
                       productDto.setTagName(product.getTagName());
                       productDto.setImageUrl(product.getImageUrl());
                       productDto.setStockQuantity(product.getStockQuantity());
                       productDto.setProductSize(product.getProductSize());

                        productDto.setCategoryId(product.getCategory().getCategoryId());
                        productDto.setCategoryName(product.getCategory().getCategoryName());

                        return productDto;
                    }
}
