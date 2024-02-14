package com.example.SpringDataJPA.controller;

import com.example.SpringDataJPA.model.Task;
import com.example.SpringDataJPA.model.TaskStatus;
import com.example.SpringDataJPA.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable TaskStatus status, Model model) {
        List<Task> tasks = taskRepository.findByStatus(status);
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @PutMapping("/{id}")
    public String updateTaskStatus(@PathVariable Long id, @ModelAttribute Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setStatus(task.getStatus());
            taskRepository.save(existingTask);
        }
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }


}








