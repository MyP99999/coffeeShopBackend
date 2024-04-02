package com.example.coffeeShop.repositories;
import com.example.coffeeShop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
