package com.rubish.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_amt")
    private Double paymentAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String status;


    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
