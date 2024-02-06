package com.mpraviap.user_service.domain.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuario")
public class User {

    @Id
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
     private String password;
     private Integer userStatus;
}
