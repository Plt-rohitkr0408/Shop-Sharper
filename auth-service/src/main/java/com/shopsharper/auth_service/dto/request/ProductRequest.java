package com.shopsharper.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Name of the product is required !!")
    private String name;

    private String description;

    @NotBlank(message = "Price Required")
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank(message ="Stock Quantity")
    @PositiveOrZero
    private Integer stock;


    @NotBlank(message = "category id")
    @NotNull
    private Long categoryId;

}
