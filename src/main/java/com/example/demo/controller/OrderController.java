package com.example.orders.controller;

import com.example.orders.entity.Order;
import com.example.orders.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/{username}")
    public List<Order> getOrdersByUsername(@PathVariable String username) {
        return repository.findByUsername(username);
    }

    @GetMapping("/seller/{sellerName}")
    public List<Order> getOrdersBySellerName(@PathVariable String sellerName) {
        return repository.findBySellerName(sellerName);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}
