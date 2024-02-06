package com.mpraviap.provider_service.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProviderRequestDto {

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
