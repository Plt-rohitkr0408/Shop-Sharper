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

    @NotNull(message = "Price Required")
    @PositiveOrZero
    private BigDecimal price;

    @NotNull(message ="Stock Quantity is not blank")
    @PositiveOrZero
    private Integer stock;


    @NotNull(message = "category id is not blank")
    private Long categoryId;

}
