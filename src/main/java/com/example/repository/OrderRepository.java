package com.example.orders.repository;

import com.example.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
    List<Order> findBySellerName(String sellerName);
    List<Order> findByImageUrl(String imageUrl);
    void deleteByImageUrl(String imageUrl);

}
