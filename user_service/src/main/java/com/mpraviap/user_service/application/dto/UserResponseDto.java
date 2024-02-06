package com.mpraviap.user_service.application.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String names;
    private String surNames;
    private String userName;
    private String documentType;
    private String documentNumber;
    private String address;
    private String phone;
    private String email;
    private String role;
    private String userStatus;
}
