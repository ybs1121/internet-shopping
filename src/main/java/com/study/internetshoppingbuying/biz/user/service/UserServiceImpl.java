package com.study.internetshoppingbuying.biz.user.service;

import com.study.internetshoppingbuying.biz.user.dto.UserDto;
import com.study.internetshoppingbuying.biz.user.entity.User;
import com.study.internetshoppingbuying.biz.user.entity.mapper.UserMapper;
import com.study.internetshoppingbuying.biz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String saveUser(UserDto userDto) {
        // 1. 존재하는 아이디 인지 확인한다.
        if (userRepository.existsByUserId(userDto.getUserId())) {
            // 2. 아이디 기준으로 존재하는 회원이면 실패를 반환한다.
            throw new IllegalArgumentException("이미 중복된 아이디 입니다. 다른 아이디를 선택해주세요");
        }

        // 3. 저장한다.
        User user = UserMapper.toEntity(userDto);
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public List<UserDto> getUsers() {
        //fixme 추후 QueryDsl 로 변경예정
        return userRepository.findAll().stream().map( u -> UserMapper.toDto(u)).collect(Collectors.toList());
    }
}
