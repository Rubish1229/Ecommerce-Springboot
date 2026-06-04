package com.rubish.ecommerce.controller;

import com.rubish.ecommerce.dto.PaymentRequest;
import com.rubish.ecommerce.model.*;
import com.rubish.ecommerce.repository.CustomerRepo;
import com.rubish.ecommerce.repository.OrderRepo;
import com.rubish.ecommerce.repository.PaymentRepo;
import com.rubish.ecommerce.repository.ProductRepo;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class RestPaymentController {

    private CustomerRepo customerRepo;
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private PaymentRepo paymentRepo;

    public RestPaymentController(CustomerRepo customerRepo, ProductRepo productRepo, OrderRepo orderRepo, PaymentRepo paymentRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
    }


    @PostMapping("/create-checkout-session")
    public Map<String ,Object> createCheckoutSession(@RequestBody PaymentRequest request) throws StripeException {

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.ALIPAY)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8011/api/payment/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl("http://localhost:8011/api/payment/cancel")

                .putMetadata("customerId",request.getCustomerId().toString())
                .putMetadata("productId",request.getProductId().toString())
                .putMetadata("quantity",request.getQuantity().toString())

                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(100L)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Test Product")
                                                                .build()
                                                )
                                                .build()
                                ).setQuantity(1L)
                                .build()
                )
                .build();
        Session session  =Session.create(params);
        Map<String ,Object> result = new HashMap<String ,Object>();
        result.put("sessionId", session.getId());
        return ResponseEntity.ok(result).getBody();
    }

    @GetMapping("/success")
    public String getSuccess(@RequestParam("session_id") String sessionId) throws Exception{
        Session session=Session.retrieve(sessionId);

        Long customerId=Long.parseLong(session.getMetadata().get("customerId"));

        Long productId=Long.parseLong(session.getMetadata().get("productId"));

        Integer quantity=Integer.parseInt(session.getMetadata().get("quantity"));
        Customer customer =
                customerRepo.findById(customerId)
                        .orElseThrow(() ->
                                new RuntimeException("Customer not found"));

        Product product =
                productRepo.findById(productId)
                        .orElseThrow(() ->
                                new RuntimeException("Product not found"));

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderTime(LocalTime.now());
        order.setStatus("PAID");
        order.setCustomer(customer);
        BigDecimal total = product.getProductPrice()
                .multiply(BigDecimal.valueOf(quantity));

        order.setTotalAmount(total);


        OrderItem item = new OrderItem();
        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentAmount(total);
        payment.setPaymentMethod("CARD");
        payment.setStatus("SUCCESS");
        payment.setOrder(order);

        order.setOrderItems(List.of(item));
        order.setPayment(payment);

        orderRepo.save(order);

        return "payment successful";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "payment canceled";
    }
}