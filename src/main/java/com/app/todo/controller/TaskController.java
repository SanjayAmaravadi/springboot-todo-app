package com.app.todo.controller;

import com.app.todo.models.Task;
import com.app.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService){
        this.taskService = taskService ;
    }

    @GetMapping
    public String getTask(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        // "tasks" -> it will search for tasks.html in resources > Templates
        // tasks -> it will get the data from the repository
        return "tasks"; // Thymeleaf search for tasks.html
    }

    @PostMapping
    public String getTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PutMapping("/{id}")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}
