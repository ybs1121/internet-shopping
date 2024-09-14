package com.study.internetshoppingbuying.biz.user.repository;

import com.study.internetshoppingbuying.biz.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserId(String userId);
}
