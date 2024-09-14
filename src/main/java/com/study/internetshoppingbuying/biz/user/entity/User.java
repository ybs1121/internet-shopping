package com.study.internetshoppingbuying.biz.user.entity;

import com.study.internetshoppingbuying.biz.user.code.GradeEnum;
import com.study.internetshoppingbuying.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(length = 20)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private GradeEnum grade;


    @Builder
    protected User(String userId, String password, String name, String address, GradeEnum grade) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.grade = grade;
    }
}
