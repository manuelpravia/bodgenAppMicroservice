package com.mpraviap.shopping_service.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingRequestDto {

    private String providerName;
    private String providerDocument;
    private double allocateAmount;
    private double  refundAmount;
    private String paymentType;
    private String shoppingStatus;
    private double priceFinal;
    private String userId;
    private List<DetailDto> detail;
}
