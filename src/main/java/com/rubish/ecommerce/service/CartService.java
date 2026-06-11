package com.rubish.ecommerce.service;

import com.rubish.ecommerce.dto.AddToCartDto;
import com.rubish.ecommerce.dto.CartItemDto;
import com.rubish.ecommerce.dto.CustomerDto;
import com.rubish.ecommerce.model.Cart;
import com.rubish.ecommerce.model.CartItem;
import com.rubish.ecommerce.model.Customer;
import com.rubish.ecommerce.model.Product;
import com.rubish.ecommerce.repository.CartItemRepo;
import com.rubish.ecommerce.repository.CartRepo;
import com.rubish.ecommerce.repository.CustomerRepo;
import com.rubish.ecommerce.repository.ProductRepo;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepo cartRepo;
    private CartItemRepo cartItemRepo;
    private ProductRepo productRepo;
    private CustomerRepo customerRepo;

    public CartService(CartRepo cartRepo, CartItemRepo cartItemRepo, ProductRepo productRepo, CustomerRepo customerRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    public CartItemDto addToCart(AddToCartDto dto, CustomerDto customerSession ){
        if(customerSession==null || customerSession.getCustomerId()==null){
            throw  new RuntimeException("Customer not logged in!");
        }
        Long customerId=customerSession.getCustomerId();

        Cart cart=cartRepo.findByCustomerCustomerId(customerId)
                .orElseGet(()->{
                    Customer customer=customerRepo.findById(customerId)
                            .orElseThrow(()-> new RuntimeException("Customer not found "));

                    Cart newCart=new Cart();
                    newCart.setCustomer(customer);

                    return cartRepo.save(newCart);
                });


        Product product=productRepo.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        CartItem cartItem=cartItemRepo.findByCartAndProduct(cart,product)
                .orElse(null);


        if(cartItem!=null){
            cartItem.setQuantity(cartItem.getQuantity()+ dto.getQuantity());
        }else{
            cartItem =new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(dto.getQuantity());
        }

        CartItem savedItem=cartItemRepo.save(cartItem);

        CartItemDto cartItemDto=new CartItemDto();
        cartItemDto.setCartItemId(savedItem.getCartItemId());
        cartItemDto.setProductId(product.getProductId());
        cartItemDto.setProductName(product.getProductName());
        cartItemDto.setPrice(product.getProductPrice());
        cartItemDto.setQuantity(savedItem.getQuantity());

        return cartItemDto;
    }


}
