package com.jeff.taskmanager.service;

import com.jeff.taskmanager.model.Task;
import com.jeff.taskmanager.repostory.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TaskService handles the business logic for Task operations.
 * It acts as a bridge between the Controller (API layer)
 * and the Repository (data access layer).
 */
@Service
public class TaskService {

    // Repository used to interact with the database
    private final TaskRepository taskRepository;

    // Constructor injection (recommended for dependency injection)
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    /**
     * Retrieves all tasks from the database
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieves a single task by its ID
     */
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    /**
     * Creates and saves a new task
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task by ID
     * Returns the updated task if found, otherwise null
     */
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    /**
     * Deletes a task by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }
}