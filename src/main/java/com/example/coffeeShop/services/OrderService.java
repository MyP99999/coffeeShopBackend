package com.example.coffeeShop.services;

import com.example.coffeeShop.dto.OrderDto;
import com.example.coffeeShop.entities.Order;
import com.example.coffeeShop.entities.Product;
import com.example.coffeeShop.entities.User;
import com.example.coffeeShop.exception.ProductNotFoundException;
import com.example.coffeeShop.exception.UserNotFoundException;
import com.example.coffeeShop.repositories.OrderRepository;
import com.example.coffeeShop.repositories.ProductRepository;
import com.example.coffeeShop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(OrderDto orderDto) {
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + orderDto.getProductId()));

        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + orderDto.getUserId()));

        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setOrderDate(orderDto.getOrderDate());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.getStatus());

        return orderRepository.save(order);
    }


    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Integer id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for this id :: " + id));

        order.setUser(orderDetails.getUser());
        order.setProduct(orderDetails.getProduct());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setQuantity(orderDetails.getQuantity());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());

        final Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }
}
