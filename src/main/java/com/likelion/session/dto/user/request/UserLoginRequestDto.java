package com.likelion.session.dto.user.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userId;
    private String password;
}