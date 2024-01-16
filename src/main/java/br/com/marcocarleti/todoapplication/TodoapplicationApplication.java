package br.com.marcocarleti.todoapplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.marcocarleti.todoapplication.tasks.Task;
import br.com.marcocarleti.todoapplication.tasks.repositories.TaskRepository;


@SpringBootApplication
@EntityScan(basePackages = "br.com.marcocarleti.todoapplication.tasks")
@EnableJpaRepositories(basePackages = "br.com.marcocarleti.todoapplication.tasks.repositories")
public class TodoapplicationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoapplicationApplication.class, args);
    }
}

