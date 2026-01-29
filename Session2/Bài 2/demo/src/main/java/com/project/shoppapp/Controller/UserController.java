package com.project.shoppapp.Controller;

import com.project.shoppapp.Models.User;
import com.project.shoppapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String username, @RequestParam(required = false) Long userid){
        {
            if (username == null) {
                return ResponseEntity.ok(userService.findAll());
            }
            if (userid != null) {
                return ResponseEntity.ok(userService.findUserById(userid));
            }
            return ResponseEntity.ok(userService.findUserByName(username));
        }
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user
    ) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        }
    }

}

