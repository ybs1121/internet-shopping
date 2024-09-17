package com.study.internetshoppingbuying.biz.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String address;


    @Builder
    public UserDto(String userId, String password, String name, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
