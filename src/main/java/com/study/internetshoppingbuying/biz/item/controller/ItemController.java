package com.study.internetshoppingbuying.biz.item.controller;

import com.study.internetshoppingbuying.biz.item.dto.ItemDto;
import com.study.internetshoppingbuying.biz.item.service.ItemService;
import com.study.internetshoppingbuying.core.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public CommonResponseDto<Long> save(@RequestBody final ItemDto itemDto) {
        return CommonResponseDto.OK(itemService.save(itemDto));
    }
}
