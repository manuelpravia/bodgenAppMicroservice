package com.mpraviap.product_service.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {

    private String name;
    private String description;
    private double price;
    private Integer stock;
    private String categoryId;
    private String code;
    private String presentation;
    private String image;
}
