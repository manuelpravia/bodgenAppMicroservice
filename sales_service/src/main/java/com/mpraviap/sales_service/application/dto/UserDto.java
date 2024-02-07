package com.mpraviap.sales_service.application.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String names;
    private String surNames;
    private String documentType;
    private String  documentNumber;
}
