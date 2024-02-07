package com.mpraviap.sales_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetail {

    private String productCode;
    private String productName;
    private Integer quantities;
    private double subTotal;
}
