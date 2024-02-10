package com.mpraviap.sales_service.application.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String names;
    private String surNames;
    private String documentType;
    private String  documentNumber;

    private String userName;
    private String address;
    private String phone;
    private String email;
    private String role;
    private Integer userStatus;
}
