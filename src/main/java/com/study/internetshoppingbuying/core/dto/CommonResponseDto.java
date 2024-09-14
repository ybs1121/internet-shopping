package com.study.internetshoppingbuying.core.dto;

import com.study.internetshoppingbuying.core.code.CommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponseDto<T> {

    private String code;
    private String message;
    private T data;

    public static <T> CommonResponseDto OK(T data) {
        return new CommonResponseDto<>(CommonResponseCode.OK.getCode(), CommonResponseCode.OK.getMessage(), data);
    }

    public static <T> CommonResponseDto FAIL(T data) {
        return new CommonResponseDto<>(CommonResponseCode.FAIL.getCode(), CommonResponseCode.FAIL.getMessage(), data);
    }

}
