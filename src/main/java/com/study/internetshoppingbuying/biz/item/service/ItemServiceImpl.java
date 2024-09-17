package com.study.internetshoppingbuying.biz.item.service;

import com.study.internetshoppingbuying.biz.item.dto.ItemDto;
import com.study.internetshoppingbuying.biz.item.entity.Item;
import com.study.internetshoppingbuying.biz.item.mapper.ItemMapper;
import com.study.internetshoppingbuying.biz.item.repository.ItemRepository;
import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import com.study.internetshoppingbuying.biz.seller.respository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;

    public Long save(ItemDto itemDto) {

        Seller seller = sellerRepository.findById(itemDto.getSellerId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다.")
        );

        Item item = ItemMapper.toEntity(itemDto, seller);
        return itemRepository.save(item).getId();
    }
}
