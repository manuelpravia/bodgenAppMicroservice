package com.mpraviap.sales_service.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaleResponseDto {
    private String id;
    private String saleCode;
    private String  customerName;
    private String customerDocument;
    private double allocateAmount;
    private double refundAmount;
    private double igv;
    private String paymentType;
    private double priceFinal;
    private String saleStatus;
    private List<DetailResponseDto> details;
}
