package com.example.coffeeShop.services;
import com.example.coffeeShop.entities.User;
import com.example.coffeeShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        System.out.println(user);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return bCryptPasswordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public List<User> getAllUsers() {
        // Assuming you have a userRepository that extends JpaRepository
        return userRepository.findAll();
    }

}
