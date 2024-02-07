package com.mpraviap.sales_service.application.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    private String code;
    private String presentation;
}
