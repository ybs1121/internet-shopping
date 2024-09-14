package com.study.internetshoppingbuying.biz.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String address;
}
