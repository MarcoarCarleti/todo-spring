package br.com.marcocarleti.todoapplication.tasks.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcocarleti.todoapplication.tasks.entities.Task;
import br.com.marcocarleti.todoapplication.tasks.services.TaskService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	// private final TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public Iterable<Task> findAllTasks() {
		return taskService.findAll();
	}

	@GetMapping("/tasks/{taskId}")

	public Optional<Task> findTaskById(@PathVariable Long taskId) {
		return taskService.findById(taskId);
	}

	@PostMapping("/tasks")
	public void addOneTask(@RequestBody Task task) {
		System.out.println("Task: " + task.getTitle());

		this.taskService.save(task);
	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<Task> updateTaskById(@PathVariable Long taskId, @RequestBody Task updatedTask) {
		return taskService.updateById(taskId, updatedTask);

	}

	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<Object> deleteTaskById(@PathVariable Long taskId) {
		return taskService.deleteById(taskId);
	}
}
