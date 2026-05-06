package com.rubish.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(name = "product_name",nullable = false,length = 30)
    private String productName;

    @Column(name = "tag_name",nullable = false,length = 50)
    private String tagName;

    @Column(name = "product_desc",columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_price",nullable = false)
    private BigDecimal productPrice;

    @Column(name = "stock_quantity")
    private int stockQuantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;


}
