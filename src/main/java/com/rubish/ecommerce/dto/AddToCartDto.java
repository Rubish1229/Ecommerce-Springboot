package com.rubish.ecommerce.dto;

import com.rubish.ecommerce.model.CartItem;
import com.rubish.ecommerce.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long customerId;
    private Long productId;
    private Integer quantity;

}
