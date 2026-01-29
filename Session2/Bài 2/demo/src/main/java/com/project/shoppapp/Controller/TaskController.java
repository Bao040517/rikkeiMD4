package com.project.shoppapp.Controller;
import com.project.shoppapp.Models.Task;
import com.project.shoppapp.Models.User;
import com.project.shoppapp.Service.TaskService;
import com.project.shoppapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false) String title
    ) {
        if (title == null) {
            return ResponseEntity.ok(taskService.getTasks());
        }
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        try {
            Task savedTask = taskService.createTask(newTask);
            return ResponseEntity.status(201).body(savedTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }





}
