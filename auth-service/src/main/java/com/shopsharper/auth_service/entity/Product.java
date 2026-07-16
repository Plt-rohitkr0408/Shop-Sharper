package com.shopsharper.auth_service.entity;


import com.shopsharper.auth_service.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(length = 100)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @Builder.Default
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status= ProductStatus.ACTIVE;
}
