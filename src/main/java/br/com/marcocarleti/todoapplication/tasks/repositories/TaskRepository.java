package br.com.marcocarleti.todoapplication.tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcocarleti.todoapplication.tasks.Task;

@Repository
public interface TaskRepository extends CustomCrudRepository<Task, Integer> {

}
