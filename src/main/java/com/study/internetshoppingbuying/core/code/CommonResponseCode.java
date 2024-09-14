package com.study.internetshoppingbuying.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonResponseCode {

    OK("OK", "정상적으로 처리 되었습니다."),
    FAIL("FAIL", "정상적으로 처리 되지 못하였습니다.")
    ;



    private final String code;
    private final String message;




}
