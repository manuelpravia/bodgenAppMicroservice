package com.mpraviap.product_service.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponseDto {

    private  String id;
    private String name;
    private String description;
    private double price;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;
    private Integer stock;
    private String categoryId;
    private String code;
    private String presentation;
    private String image;
}
