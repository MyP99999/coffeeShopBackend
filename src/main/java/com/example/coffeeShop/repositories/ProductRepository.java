package com.example.coffeeShop.repositories;
import com.example.coffeeShop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
