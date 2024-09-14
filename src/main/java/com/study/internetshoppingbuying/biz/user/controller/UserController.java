package com.study.internetshoppingbuying.biz.user.controller;

import com.study.internetshoppingbuying.biz.user.dto.UserDto;
import com.study.internetshoppingbuying.biz.user.service.UserService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    @Value("${biz.is-master}")
    private String isMaster;
    private final UserService userService;

    @PostMapping
    public CommonResponseDto<String> saveUser(@RequestBody final UserDto userDto) {
        return CommonResponseDto.OK(userService.saveUser(userDto));
    }


    @GetMapping("")
    public CommonResponseDto<List<UserDto>> getAllUser(HttpServletRequest request) throws IllegalAccessException {
        if (StringUtils.isEmpty(request.getHeader("is-master"))
                || !isMaster.equals(request.getHeader("is-master"))) {
            throw new IllegalAccessException("권한이 없는 사용자입니다.");
        }
        return CommonResponseDto.OK(userService.getUsers());
    }
}
