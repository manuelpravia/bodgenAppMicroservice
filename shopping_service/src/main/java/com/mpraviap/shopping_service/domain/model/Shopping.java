package com.mpraviap.shopping_service.domain.model;

import com.mpraviap.shopping_service.model.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shopping")
public class Shopping {

    @Id
    private String id;
    private String providerName;
    private String providerDocument;
    private double allocateAmount;
    private double refundAmount;
    private String paymentType;
    private String shoppingStatus;
    private double priceFinal;
    private String userId;
    private List<Detail> detail;
}
