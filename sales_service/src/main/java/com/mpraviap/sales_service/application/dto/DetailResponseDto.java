package com.mpraviap.sales_service.application.dto;

import lombok.Data;



@Data
public class DetailResponseDto {
    private String id;
    private String quantities;
    private String subTotal;
    private ProductDto products;
}
