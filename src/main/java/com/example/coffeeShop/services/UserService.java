package com.example.coffeeShop.services;
import com.example.coffeeShop.entities.Role;
import com.example.coffeeShop.entities.User;
import com.example.coffeeShop.repositories.RoleRepository;
import com.example.coffeeShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Assuming you have this interface

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        System.out.println(user);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role defaultRole = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRole(defaultRole);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }


    public List<User> getAllUsers() {
        // Assuming you have a userRepository that extends JpaRepository
        return userRepository.findAll();
    }

}
