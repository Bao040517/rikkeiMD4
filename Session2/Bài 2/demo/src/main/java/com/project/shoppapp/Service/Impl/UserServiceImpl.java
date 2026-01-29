package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.Models.User;
import com.project.shoppapp.Repository.UserRepository;
import com.project.shoppapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findUsersByNameContainingIgnoreCase(name);
    }

    @Override
    public List<User> findUserById(Long id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setMail(user.getMail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public Boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
