package com.rubish.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "contact")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name",length = 50,nullable = false)
    private String customerName;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    @Size(min = 8, max = 16)
    private String password;

    @Column(name = "contact",nullable = false,length = 10)
    private String contact;

    @Column(name = "gender",nullable = false,length = 10)
    private String gender;


    @Column(name = "address",nullable = false,length = 30)
    private String address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Review> reviews;
}
