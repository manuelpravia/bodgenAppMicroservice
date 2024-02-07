package com.mpraviap.sales_service.application.dto;

import lombok.Data;

@Data
public class DetailRequestDto {
    private String productCode;
    private String productName;
    private Integer quantities;
    private double subTotal;
}
