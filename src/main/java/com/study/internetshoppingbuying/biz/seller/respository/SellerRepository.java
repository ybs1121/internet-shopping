package com.study.internetshoppingbuying.biz.seller.respository;

import com.study.internetshoppingbuying.biz.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
