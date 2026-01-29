package com.project.shoppapp.Service;

import com.project.shoppapp.Models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    List<User> findUserByName(String name);
    List<User> findUserById(Long id);
    User createUser(User user);
    User updateUser(Long id,User user);
    Boolean deleteUser(Long id);

}
