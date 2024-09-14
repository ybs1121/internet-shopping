package com.study.internetshoppingbuying.biz.user.entity.mapper;

import com.study.internetshoppingbuying.biz.user.code.GradeEnum;
import com.study.internetshoppingbuying.biz.user.dto.UserDto;
import com.study.internetshoppingbuying.biz.user.entity.User;
import org.springframework.stereotype.Component;


public class UserMapper {


    public static User toEntity(UserDto dto) {
        return User.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .name(dto.getName())
                .address(dto.getAddress())
                .grade(GradeEnum.NEW)
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .address(user.getAddress())
                .name(user.getName())
                .build();
    }
}
