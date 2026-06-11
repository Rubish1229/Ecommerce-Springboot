package com.rubish.ecommerce.repository;

import com.rubish.ecommerce.model.Cart;
import com.rubish.ecommerce.model.CartItem;
import com.rubish.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
