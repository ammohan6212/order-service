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
    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @DeleteMapping("/delete-by-image")
    public void deleteOrderByImageUrl(@RequestParam String imageUrl) {
        List<Order> orders = repository.findByImageUrl(imageUrl);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found with imageUrl: " + imageUrl);
        }
        repository.deleteByImageUrl(imageUrl);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}

