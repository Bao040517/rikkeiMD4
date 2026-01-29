package com.project.shoppapp.Controller;

import com.github.javafaker.Faker;
import com.project.shoppapp.Models.Enums.Role;
import com.project.shoppapp.Models.Product;
import com.project.shoppapp.Models.Task;
import com.project.shoppapp.Models.User;
import com.project.shoppapp.Repository.ProductRepository;
import com.project.shoppapp.Repository.TaskRepository;
import com.project.shoppapp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class FakerDulieu {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/seed-products")
    public String seedProducts() {
        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {
            Product p = new Product();
            p.setName(faker.commerce().productName());
            p.setPrice(faker.commerce().price());

            productRepository.save(p);
        }

        return "Đã seed 20 products!";
    }
    @GetMapping("/seed-users")
    public String seedUsers() {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setMail(faker.internet().emailAddress());
            if (random.nextInt(100) < 2) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            userRepository.save(user);
        }

        return "Đã seed 20 users!";
    }
    @GetMapping("/seed-tasks")
    public String seedTasks() {
        Faker faker = new Faker();
        Random random = new Random();

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return "Chưa có user, seed user trước!";
        }

        String[] priorities = {"low", "medium", "high"};

        for (int i = 0; i < 50; i++) {
            Task task = new Task();
            task.setTitle(faker.job().title());
            task.setDescription(faker.lorem().sentence());
            task.setPriority(priorities[random.nextInt(priorities.length)]);
            task.setAssignedTo(users.get(random.nextInt(users.size())).getId());

            taskRepository.save(task);
        }

        return "Đã seed 50 tasks!";
    }
}
