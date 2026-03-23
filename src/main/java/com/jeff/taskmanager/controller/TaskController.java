package com.jeff.taskmanager.controller;

import com.jeff.taskmanager.model.Task;
import com.jeff.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * TaskController handles all REST API requests related to tasks.
 * It acts as the entry point for client interactions (e.g., Postman, frontend).
 */

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET /api/tasks
     * Retrieves all tasks from the database
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * GET /api/tasks/{id}
     * Retrieves a single task by its ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/tasks
     * Creates a new task
     */
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * PUT /api/tasks/{id}
     * Updates an existing task by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.updateTask(id, task);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    /**
     * DELETE /api/tasks/{id}
     * Deletes a task by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}