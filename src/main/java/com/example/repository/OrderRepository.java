package com.example.orders.repository;
import com.example.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
    List<Order> findBySellerName(String sellerName);
    List<Order> findByImageUrl(String imageUrl);

    @Modifying
    @Transactional
    void deleteByImageUrl(String imageUrl);

}
