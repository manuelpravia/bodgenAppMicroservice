package com.mpraviap.sales_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sale")
public class Sale {

    @Id
    private String id;
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
    private List<SaleDetail> detail;
}
