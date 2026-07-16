package com.shopsharper.auth_service.Mapper;

import com.shopsharper.auth_service.dto.request.ProductRequest;
import com.shopsharper.auth_service.dto.response.ResponseProduct;
import com.shopsharper.auth_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setStatus(productRequest.getStatus());

        return product;
    }

    public ResponseProduct toResponse(Product product){
        ResponseProduct responseProduct = ResponseProduct.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory().getName())
                .id(product.getId())
                .active(product.getActive())
                .status(product.getStatus())
                .description(product.getDescription())
                .build();

        return responseProduct;
    }
}
