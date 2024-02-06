package com.mpraviap.provider_service.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "provider")
public class Provider {

    @Id
    private String id;
    private String names;
    private String surNames;
    private String documentType;
    private String documentNumber;
    private String address;
    private String phone;
    private String email;
    private Integer userStatus;
    private LocalDateTime createdAt;
}
