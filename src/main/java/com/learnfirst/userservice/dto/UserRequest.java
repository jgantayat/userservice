package com.learnfirst.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;
    private int age;
    private long phoneNumber;
    private String email;
    private String password;
}
