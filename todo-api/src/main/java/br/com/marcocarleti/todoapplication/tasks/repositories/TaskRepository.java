package br.com.marcocarleti.todoapplication.tasks.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcocarleti.todoapplication.tasks.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByCustomerEmail(String Email);

}
