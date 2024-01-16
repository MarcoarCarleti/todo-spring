package br.com.marcocarleti.todoapplication.tasks.repositories;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CustomCrudRepository<T, ID> extends Repository<T, ID> {
	 <S extends T> S save(S entity);
	  
	  <S extends T> Iterable<S> saveAll(Iterable<S> entities);
	  
	  Optional<T> findById(Long id);
	  
	  boolean existsById(ID id);
	  
	  Iterable<T> findAll();
	  
	  Iterable<T> findAllById(Iterable<ID> ids);
	  
	  long count();
	  
	  void deleteById(Long id);
	  
	  void delete(T entity);
	  
	  void deleteAllById(Iterable<? extends ID> ids);
	  
	  void deleteAll(Iterable<? extends T> entities);
	  
	  void deleteAll();
}
