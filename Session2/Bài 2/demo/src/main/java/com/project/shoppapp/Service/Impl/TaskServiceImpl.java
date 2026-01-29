package com.project.shoppapp.Service.Impl;

import com.project.shoppapp.Models.Task;
import com.project.shoppapp.Models.User;
import com.project.shoppapp.Repository.TaskRepository;
import com.project.shoppapp.Repository.UserRepository;
import com.project.shoppapp.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
    @Override
    public List<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Task createTask(Task task) {
       userRepository.findById(task.getAssignedTo()).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task does not exist"));

        userRepository.findById(task.getAssignedTo())
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setAssignedTo(task.getAssignedTo());

        return taskRepository.save(existingTask);
    }

    @Override
    public Boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
