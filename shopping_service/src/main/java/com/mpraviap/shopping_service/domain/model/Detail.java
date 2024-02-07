package com.mpraviap.shopping_service.domain.model;

import lombok.Data;

@Data
public class Detail {

    private String productName;
    private String productCode;
    private String description;
    private double price;
    private Integer quantities;
    private String presentation;
    private double subTotal;
}
