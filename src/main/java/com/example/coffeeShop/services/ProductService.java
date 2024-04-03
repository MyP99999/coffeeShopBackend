package com.example.coffeeShop.services;

import com.example.coffeeShop.entities.Product;
import com.example.coffeeShop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setProductId(id);
            return productRepository.save(updatedProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
