package com.project.demo.DTO.request;

import lombok.Data;

@Data
public class ActiveUserRequest {
    private String email;
    private String otp;
}
