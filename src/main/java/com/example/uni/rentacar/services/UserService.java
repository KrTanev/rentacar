package com.example.uni.rentacar.services;

import org.springframework.stereotype.Service;

import com.example.uni.rentacar.model.User;
import com.example.uni.rentacar.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setHasAccidents(updatedUser.isHasAccidents());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
