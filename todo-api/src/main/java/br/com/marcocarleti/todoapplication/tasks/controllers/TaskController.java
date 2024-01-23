package br.com.marcocarleti.todoapplication.tasks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	@GetMapping("/tasks/customer/{customerEmail}")
	public List<Task> getTasksByCustomerEmailAndTitle(
	        @PathVariable String customerEmail,
	        @RequestParam(required = false) String title
	) {
	    if (title == null) {
	        return taskService.findByCustomerEmail(customerEmail);
	    } else {     
	        return taskService.getTasksByCustomerEmailAndTitle(customerEmail, title);
	    }
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("tasks/filterByTitle")
    public List<Task> filterTasksByTitle(@RequestParam("title") String title) {
        
        return taskService.findByFilter(title);
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("tasks/filterByDone")
	 public List<Task> getTasksByDone(@RequestParam(value = "done", required = false) Boolean done) {
	        if (done != null) {
	            return taskService.findTasksByDone(done);
	        } else {
	           
	            return (List<Task>) taskService.findAll();
	        }
	    }
	
	 @GetMapping("tasks/done/{customerEmail}")
	    public List<Task> getTasksByDoneAndEmail(
	    		@PathVariable String customerEmail,
	            @RequestParam(value = "done", required = false) Boolean done
	            ) {
	        if (done != null) {
	        	return taskService.findTasksByDoneAndCustomerEmail(done, customerEmail);
	        } else {
	        	return (List<Task>) taskService.findByCustomerEmail(customerEmail);
	        }
	    }
	
	
	@GetMapping("/tasks/{customerEmail}")
	public List<Task> findTaskByCustomerId(@PathVariable String customerEmail) {
		return taskService.findByCustomerEmail(customerEmail);
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
