package br.com.marcocarleti.todoapplication.tasks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.marcocarleti.todoapplication.tasks.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByCustomerEmail(String Email);

	@Query("SELECT t FROM Task t WHERE t.title LIKE %?1%")
	List<Task> findByFilter(String title);

	@Query("SELECT t FROM Task t WHERE t.done = ?1")
	List<Task> findByDone(boolean done);

	List<Task> findByCustomerEmailAndTitleContaining(String customerEmail, String title);
	
    List<Task> findByDoneAndCustomerEmail(Boolean done, String customerEmail);

    @Query("SELECT t FROM Task t WHERE t.customerEmail LIKE %?1%")
    List<Task> findByFilterCustomerEmail(String customerEmail);
}
