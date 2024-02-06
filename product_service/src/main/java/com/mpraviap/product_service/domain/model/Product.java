package com.mpraviap.product_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private  String id;
    private String name;
    private String description;
    private double price;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;
    private Integer stock;
    private String categoryId;
    private String code;
    private String presentation;
    private String image;
}
