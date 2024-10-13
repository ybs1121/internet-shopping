package com.study.internetshoppingbuying.biz.item.repository;

import com.study.internetshoppingbuying.biz.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsById(Long id);

    List<Item> findByIdIn(List<Long> ids);
    List<Item> findByIdIn(Set<Long> ids);
}
