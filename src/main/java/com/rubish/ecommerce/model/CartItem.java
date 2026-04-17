package com.rubish.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItem_id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_item")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
