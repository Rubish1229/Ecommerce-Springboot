package com.rubish.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",length = 10,nullable = false)
    private Long categoryId;

    @Column(name = "category_name",length = 50)
    private String categoryName;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private List<Product> products;
}
