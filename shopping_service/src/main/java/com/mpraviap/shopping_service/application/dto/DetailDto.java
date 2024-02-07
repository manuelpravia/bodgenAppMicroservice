package com.mpraviap.shopping_service.application.dto;

import lombok.Data;

@Data
public class DetailDto {
    private String productName;
    private String productCode;
    private String description;
    private double price;
    private Integer quantities;
    private String presentation;
    private double subTotal;
}
