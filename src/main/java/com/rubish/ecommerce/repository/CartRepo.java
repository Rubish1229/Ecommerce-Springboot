package com.rubish.ecommerce.repository;

import com.rubish.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long>{
    Optional<Cart> findByCustomerCustomerId(Long customerId);

}
