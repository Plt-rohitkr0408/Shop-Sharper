package com.shopsharper.auth_service.dto.response;

import com.shopsharper.auth_service.enums.ProductStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ResponseProduct {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String description;
    private boolean active;
    private ProductStatus status;
}

