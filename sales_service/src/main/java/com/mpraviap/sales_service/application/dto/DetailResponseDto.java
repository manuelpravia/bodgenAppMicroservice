package com.mpraviap.sales_service.application.dto;

import lombok.Data;

import java.util.List;


@Data
public class DetailResponseDto {
    private String productCode;
    private String productName;
    private Integer quantities;
    private double subTotal;
}
