package com.mpraviap.shopping_service.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingResponseDto {
    private String id;
    private String providerName;
    private String providerDocument;
    private double allocateAmount;
    private double refundAmount;
    private String paymentType;
    private double priceFinal;
    private String shoppingStatus;
    private UserDto user;
    private List<DetailDto> detail;
}
