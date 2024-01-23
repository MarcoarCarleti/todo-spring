package br.com.marcocarleti.todoapplication.tasks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.marcocarleti.todoapplication.tasks.entities.Task;
import br.com.marcocarleti.todoapplication.tasks.exceptions.TaskNotFoundException;
import br.com.marcocarleti.todoapplication.tasks.repositories.CustomCrudRepository;
import br.com.marcocarleti.todoapplication.tasks.repositories.TaskRepository;

@Service
public class TaskService implements CustomCrudRepository<Task, Long> {

	@Autowired
	private TaskRepository taskrepository;
	
	@Override
	public <S extends Task> S save(S entity) {
		// TODO Auto-generated method stub
		return taskrepository.save(entity);
	}

	@Override
	public <S extends Task> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Task> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.of(taskrepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Não foi possível encontrar uma task com o id " + id)));
	}

	

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Task> findByCustomerEmail(String Email) {
		return taskrepository.findByCustomerEmail(Email);
	}

	@Override
	public Iterable<Task> findAll() {
		// TODO Auto-generated method stub
		return taskrepository.findAll();
	}

	@Override
	public Iterable<Task> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseEntity<Object> deleteById(Long id) {
		// TODO Auto-generated method stub
		return taskrepository.findById(id).map(existingTask -> {
			taskrepository.delete(existingTask);
    		return ResponseEntity.noContent().build();
    	}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public void delete(Task entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Task> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ResponseEntity<Task> updateById(Long id, Task updatedTask) {
		// TODO Auto-generated method stub
		return taskrepository.findById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDone(updatedTask.getDone());

            Task updated = taskrepository.save(existingTask);
            return ResponseEntity.ok().body(updated);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
	}


	public List<Task> findByFilter(String seuValor){
		
		return taskrepository.findByFilter(seuValor);
		
	}
	
	public List<Task> findTasksByDone(boolean done) {
        return taskrepository.findByDone(done);
    }
	
	public List<Task> getTasksByCustomerEmailAndTitle(String customerEmail, String title) {
        return taskrepository.findByCustomerEmailAndTitleContaining(customerEmail, title);
    }
	
	public List<Task> findTasksByDoneAndCustomerEmail(Boolean done, String customerEmail) {
        return taskrepository.findByDoneAndCustomerEmail(done, customerEmail);
    }

	

}
