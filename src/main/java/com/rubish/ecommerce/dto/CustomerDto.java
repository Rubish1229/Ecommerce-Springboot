package com.rubish.ecommerce.dto;

import com.rubish.ecommerce.model.Cart;
import com.rubish.ecommerce.model.Order;
import com.rubish.ecommerce.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long customerId;
    private String customerName;
    private String email;
    private String password;
    private String contact;
    private String gender;
    private String address;

}
