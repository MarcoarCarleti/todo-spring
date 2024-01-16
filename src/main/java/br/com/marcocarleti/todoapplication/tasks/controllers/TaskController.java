package br.com.marcocarleti.todoapplication.tasks.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcocarleti.todoapplication.tasks.Task;
import br.com.marcocarleti.todoapplication.tasks.exceptions.TaskNotFoundException;
import br.com.marcocarleti.todoapplication.tasks.repositories.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public Iterable<Task> findAllTasks() {
        return this.taskRepository.findAll();
    }
    
    @GetMapping("/{taskId}")
    public Task findTaskById(@PathVariable Long taskId) {
        return this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Não foi possível encontrar uma task com o id " + taskId));
    }

    @PostMapping("/")
    public void addOneTask(@RequestBody Task task) {
        System.out.println("Task: " + task.getTitle());

        this.taskRepository.save(task);
    }
    
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        return taskRepository.findById(taskId)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setDone(updatedTask.getDone());

                    Task updated = taskRepository.save(existingTask);
                    return ResponseEntity.ok().body(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long taskId) {
    	return taskRepository.findById(taskId).map(existingTask -> {
    		taskRepository.delete(existingTask);
    		return ResponseEntity.noContent().build();
    	}).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
