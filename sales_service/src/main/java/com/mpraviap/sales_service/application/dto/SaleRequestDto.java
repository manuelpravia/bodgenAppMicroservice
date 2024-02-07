package com.mpraviap.sales_service.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequestDto {
    private String saleCode;
    private String customerName;
    private String customerDocument;
    private double allocateAmount;
    private double refundAmount;
    private double igv;
    private String paymentType;
    private String saleStatus;
    private double priceFinal;
    private String userId;
    private List<DetailRequestDto> detail;
}
