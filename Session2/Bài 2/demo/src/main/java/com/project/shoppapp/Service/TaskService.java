package com.project.shoppapp.Service;

import com.project.shoppapp.Models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    List<Task> getTaskByTitle(String title);
    Task createTask(Task task);
    Task updateTask(Long id , Task task);
    Boolean deleteTask(Long id);
}
