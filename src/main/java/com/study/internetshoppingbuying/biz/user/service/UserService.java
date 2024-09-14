package com.study.internetshoppingbuying.biz.user.service;

import com.study.internetshoppingbuying.biz.user.dto.UserDto;

import java.util.List;

public interface UserService {

    String saveUser(UserDto userDto);

    List<UserDto> getUsers();
}
